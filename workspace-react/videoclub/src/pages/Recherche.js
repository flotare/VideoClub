import React, { useState, useEffect, useMemo } from "react";
import VideoCard from "../components/VideoCard"; // importer ton VideoCard
import './Recherche.css';

export default function Recherche() {
  const [videos, setVideos] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedTag, setSelectedTag] = useState('Tous');

  // Charger les films depuis la BDD
  useEffect(() => {
    fetch('/videos?type=films')
      .then(res => res.json())
      .then(data => setVideos(data || []))
      .catch(console.error);
  }, []);

  // Extraire tous les tags disponibles à partir des vidéos
  const ALL_TAGS = useMemo(() => {
    const tags = new Set();

    videos.forEach(video => {
        // Supposons que les genres sont dans video.tagsGenre qui est un tableau d'objets
        if (video.tagsGenre && Array.isArray(video.tagsGenre)) {
        video.tagsGenre.forEach(tagObj => {
            if (tagObj.genreName) {
            tags.add(tagObj.genreName);
            }
        });
        }
    });

    return ['Tous', ...Array.from(tags)];
  }, [videos]);



  // Filtrer les vidéos selon recherche et tag
  const filteredVideos = useMemo(() => {
    let results = videos;

    if (searchTerm) {
      const lowerSearch = searchTerm.toLowerCase();
      results = results.filter(v =>
        v.title?.toLowerCase().includes(lowerSearch) ||
        v.director?.toLowerCase().includes(lowerSearch)
      );
    }

    if (selectedTag !== 'Tous') {
        results = results.filter(video => 
            video.tagsGenre && video.tagsGenre.some(tagObj => tagObj.genreName === selectedTag)
        );
    }


    return results;
  }, [searchTerm, selectedTag, videos]);

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleTagClick = (tag) => {
    setSelectedTag(prev => prev === tag ? 'Tous' : tag);
  };

  return (
    <div className="recherche-container">
      <h2 className="text-4xl font-extrabold text-center mb-6">Rechercher un Film</h2>

      {/* Barre de recherche */}
      <div className="search-bar-container">
        <input
          type="text"
          placeholder="Entrez le titre"
          value={searchTerm}
          onChange={handleInputChange}
          className="search-input"
        />
      </div>

      {/* Filtres tags */}
      <div className="tags-container">
        {ALL_TAGS.map(tag => (
          <button
            key={tag}
            onClick={() => handleTagClick(tag)}
            className={`tag-button ${selectedTag === tag ? 'selected' : ''}`}
          >
            {tag}
          </button>
        ))}
      </div>

      {/* Résultats */}
      <p className="result-count text-center mb-6">
        {filteredVideos.length} résultat{filteredVideos.length > 1 ? 's' : ''}
      </p>

      {/* Liste vidéos avec VideoCard */}
      <ul className="video-list">
        {filteredVideos.map(video => (
          <VideoCard key={video.idVideo} current_video={video} />
        ))}
      </ul>
    </div>
  );
}
