import { useState } from "react";
import "./AjoutElementBDD.css";

function AjoutElementBDD() {
  // ------------------- STATE FORMULAIRE VIDEO -------------------
  const [videoForm, setVideoForm] = useState({
    title: "",
    description: "",
    releaseDate: "",
    imagePath: "",
    episodeNumber: "",
    previousVideoId: "",
    nextVideoId: "",
    seasonId: "",
    tags: ""
  });

  // ------------------- STATE FORMULAIRE GENRE -------------------
  const [genreForm, setGenreForm] = useState({ genreName: "" });

  // ------------------- STATE FORMULAIRE ACTEUR -------------------
  const [actorForm, setActorForm] = useState({ firstName: "", lastName: "" });

  // ------------------- HANDLERS VIDEO -------------------
  const handleVideoChange = (e) => {
    const { name, value } = e.target;
    setVideoForm({ ...videoForm, [name]: value });
  };

  const handleVideoSubmit = async (e) => {
    e.preventDefault();

    // Transforme les tags en tableau de nombres
    const tagIds = videoForm.tags
      .split(",")
      .map((id) => parseInt(id.trim()))
      .filter((id) => !isNaN(id));

    const payload = {
      ...videoForm,
      episodeNumber: videoForm.episodeNumber || null,
      previousVideoId: videoForm.previousVideoId || null,
      nextVideoId: videoForm.nextVideoId || null,
      seasonId: videoForm.seasonId || null,
      tags: tagIds
    };

    try {
      const res = await fetch("/videos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      if (!res.ok) throw new Error("Erreur lors de la création de la vidéo");

      alert("Vidéo créée avec succès !");
      setVideoForm({
        title: "",
        description: "",
        releaseDate: "",
        imagePath: "",
        episodeNumber: "",
        previousVideoId: "",
        nextVideoId: "",
        seasonId: "",
        tags: ""
      });
    } catch (err) {
      console.error(err);
      alert("Erreur lors de la création de la vidéo");
    }
  };

  // ------------------- HANDLERS GENRE -------------------
  const handleGenreChange = (e) => {
    setGenreForm({ genreName: e.target.value });
  };

  const handleGenreSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("/tags/genre", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(genreForm)
      });

      if (!res.ok) throw new Error("Erreur lors de la création du genre");

      alert("Genre créé avec succès !");
      setGenreForm({ genreName: "" });
    } catch (err) {
      console.error(err);
      alert("Erreur lors de la création du genre");
    }
  };

  // ------------------- HANDLERS ACTEUR -------------------
  const handleActorChange = (e) => {
    const { name, value } = e.target;
    setActorForm({ ...actorForm, [name]: value });
  };

  const handleActorSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("/tags/actor", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(actorForm)
      });

      if (!res.ok) throw new Error("Erreur lors de la création de l'acteur");

      alert("Acteur créé avec succès !");
      setActorForm({ firstName: "", lastName: "" });
    } catch (err) {
      console.error(err);
      alert("Erreur lors de la création de l'acteur");
    }
  };

  return (
    <div className="admin-panel">
      <h1>Panel Admin — Ajout de Données</h1>

      {/* FORMULAIRE AJOUT VIDEO */}
      <h2>Ajouter une Vidéo</h2>
      <form onSubmit={handleVideoSubmit}>
        <label>Titre</label>
        <input type="text" name="title" value={videoForm.title} onChange={handleVideoChange} required />

        <label>Description</label>
        <textarea name="description" rows="4" value={videoForm.description} onChange={handleVideoChange} required />

        <label>Date de sortie</label>
        <input type="date" name="releaseDate" value={videoForm.releaseDate} onChange={handleVideoChange} required />

        <label>Chemin de l'image</label>
        <input type="text" name="imagePath" value={videoForm.imagePath} onChange={handleVideoChange} />

        <label>Numéro d'épisode (optionnel)</label>
        <input type="number" name="episodeNumber" value={videoForm.episodeNumber} onChange={handleVideoChange} />

        <label>ID de la vidéo précédente (optionnel)</label>
        <input type="number" name="previousVideoId" value={videoForm.previousVideoId} onChange={handleVideoChange} />

        <label>ID de la vidéo suivante (optionnel)</label>
        <input type="number" name="nextVideoId" value={videoForm.nextVideoId} onChange={handleVideoChange} />

        <label>ID de la saison (optionnel)</label>
        <input type="number" name="seasonId" value={videoForm.seasonId} onChange={handleVideoChange} />

        <label>Tags associés (IDs séparés par des virgules)</label>
        <input type="text" name="tags" value={videoForm.tags} onChange={handleVideoChange} />

        <button type="submit">Créer la vidéo</button>
      </form>

      {/* FORMULAIRE AJOUT TAG GENRE */}
      <h2>Ajouter un Tag (Genre)</h2>
      <form onSubmit={handleGenreSubmit}>
        <label>Nom du genre</label>
        <input type="text" value={genreForm.genreName} onChange={handleGenreChange} required />
        <button type="submit">Créer le tag genre</button>
      </form>

      {/* FORMULAIRE AJOUT TAG ACTEUR */}
      <h2>Ajouter un Tag (Acteur)</h2>
      <form onSubmit={handleActorSubmit}>
        <label>Prénom</label>
        <input type="text" name="firstName" value={actorForm.firstName} onChange={handleActorChange} required />

        <label>Nom</label>
        <input type="text" name="lastName" value={actorForm.lastName} onChange={handleActorChange} required />

        <button type="submit">Créer le tag acteur</button>
      </form>
    </div>
  );
}

export default AjoutElementBDD;
