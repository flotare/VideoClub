import { useState } from "react";
import "./AjoutElement.css";

export default function AjoutElementVideo() {
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

    return (
        <div className="admin-panel"> {/* FORMULAIRE AJOUT VIDEO */}
            <h2>Ajouter une Vidéo</h2>
            <form onSubmit={handleVideoSubmit}>
                <label>Titre</label>
                <input type="text" name="title" value={videoForm.title} onChange={handleVideoChange} required />

                <label>Description</label>
                <textarea name="description" rows="4" value={videoForm.description} onChange={handleVideoChange} />

                <label>Date de sortie</label>
                <input type="date" name="releaseDate" value={videoForm.releaseDate} onChange={handleVideoChange} />

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
        </div>
    );
}