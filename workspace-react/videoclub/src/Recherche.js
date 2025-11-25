import React, { useState, useMemo } from 'react';
import { MOCK_FILMS } from './Data'; // Importation des données corrigée

// Détermination des tags uniques pour les boutons de filtre
const ALL_TAGS = ['Tous', ...new Set((MOCK_FILMS || []).flatMap(film => film.tags))];

export default function Recherche() {
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedTag, setSelectedTag] = useState('Tous'); // État pour le tag de filtre
    const [showSuggestions, setShowSuggestions] = useState(false);

    // Logique de recherche (titre/réalisateur)
    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
        setShowSuggestions(true);
    };

    // Logique de sélection des tags
    const handleTagClick = (tag) => {
    // Si on clique sur le tag déjà sélectionné → on le désactive
    if (selectedTag === tag) {
        setSelectedTag('Tous');
    } else {
        setSelectedTag(tag);
    }
    };

    // Suggestions basées sur titres et réalisateurs
    const suggestions = useMemo(() => {
        if (!searchTerm) return [];

        const lowerSearch = searchTerm.toLowerCase();
        const suggestionSet = new Set();

        MOCK_FILMS.forEach(film => {
            if (film.title.toLowerCase().includes(lowerSearch)) {
                suggestionSet.add(film.title);
            }
            if (film.director.toLowerCase().includes(lowerSearch)) {
                suggestionSet.add(film.director);
            }
        });

        return Array.from(suggestionSet).slice(0, 5); // max 5 suggestions
    }, [searchTerm]);

    // Filtrage des films basé sur le terme de recherche ET le tag sélectionné
    const filteredFilms = useMemo(() => {
        let results = MOCK_FILMS || [];

        if (searchTerm) {
            const lowerCaseSearch = searchTerm.toLowerCase();
            results = results.filter(film =>
                film.title.toLowerCase().includes(lowerCaseSearch) ||
                film.director.toLowerCase().includes(lowerCaseSearch)
            );
        }

        if (selectedTag !== 'Tous') {
            results = results.filter(film =>
                (film.tags || []).includes(selectedTag)
            );
        }

        return results;
    }, [searchTerm, selectedTag]);

    // Quand on clique sur une suggestion, remplir le champ et cacher suggestions
    const onSuggestionClick = (text) => {
        setSearchTerm(text);
        setShowSuggestions(false);
    };

    return (
        <div className="p-4 sm:p-8 max-w-7xl mx-auto relative">
            <h2 className="text-4xl font-extrabold text-center mb-6 text-gray-900 border-b pb-4">
                Rechercher un Film
            </h2>

            {/* 1. Barre de Recherche */}
            <div className="flex justify-center mb-6 relative w-full max-w-lg">
                <input
                    type="text"
                    placeholder="Entrez le titre ou le réalisateur..."
                    value={searchTerm}
                    onChange={handleInputChange}
                    onFocus={() => searchTerm && setShowSuggestions(true)}
                    onBlur={() => setTimeout(() => setShowSuggestions(false), 150)} // délai pour clic
                    className="w-full py-3 px-4 border-2 border-indigo-300 rounded-xl shadow-lg focus:outline-none focus:ring-4 focus:ring-indigo-200 transition duration-150"
                    autoComplete="off"
                />
            </div>

            {/* 2. Filtres par Tags */}
            <div className="flex flex-wrap justify-center gap-2 mb-10">
                {ALL_TAGS.map(tag => (
                    <button
                        key={tag}
                        onClick={() => handleTagClick(tag)}
                        className={`px-4 py-1.5 rounded-full text-sm font-semibold transition duration-150 shadow-md ${
                            selectedTag === tag
                                ? 'bg-indigo-600 text-white shadow-indigo-400/50'
                                : 'bg-gray-100 text-gray-700 hover:bg-indigo-50 hover:text-indigo-600'
                        }`}
                    >
                        {tag}
                    </button>
                ))}
            </div>

            {/* 3. Affichage des résultats */}
            <p className="text-center text-lg mb-6 text-gray-600">
                {filteredFilms.length} résultat{filteredFilms.length > 1 ? 's' : ''} trouvé{filteredFilms.length > 1 ? 's' : ''}.
            </p>

            {/* 4. Catalogue Grid */}
            <ul className="film-list">
                {filteredFilms.map(film => (
                    <li
                        key={film.id}
                        className="App-body-image"
                    >
                        <div className="group">
                            <img
                                src={film.imageUrl}
                                alt={`Affiche du film ${film.title}`}
                                className="w-[200px] h-[300px] object-cover block transition duration-500 group-hover:scale-105"
                            />
                            <p className="text-center p-3 text-base text-gray-700 font-medium">
                                {film.title}
                            </p>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}
