import { useEffect, useState } from "react";
import "./AjoutElement.css";

export default function AjoutElementVideo() {
    // ------------------- STATE FORMULAIRE VIDEO -------------------

    const [videoForm, setVideoForm] = useState({
        type: "FILM",
        title: "",
        description: "",
        releaseDate: "",
        imagePath: "",
        tagGenre: "",
        tagActeur: "",
        numberSeason: ""  // Seulememnt pour les séries
    });

    const [genreList, setGenreList] = useState([])
    const [actorList, setActorList] = useState([])

    // Récupération de tous les genres
    useEffect(() => {
        fetch(`/api/tags/genres`)
            .then(res => res.json())
            .then(data => setGenreList(data))
            .catch(err => console.error(err));
    }, []);

    // Récupération de tous les acteurs
    useEffect(() => {
        fetch(`/api/tags/actors`)
            .then(res => res.json())
            .then(data => setActorList(data))
            .catch(err => console.error(err));
    }, []);


    // ------------------- HANDLERS VIDEO -------------------
    const handleVideoChange = (e) => {
        const { name, value } = e.target;
        setVideoForm({ ...videoForm, [name]: value });
    };

    const handleVideoSubmit = async (e) => {
        e.preventDefault();

        // Transforme les tags en tableau de string
        const genres = videoForm.tagGenre
            .split(",")
            .map(s => s.trim())
            .filter(s => s.length > 0);

        const actors = videoForm.tagActeur
            .split(",")
            .map(s => s.trim())
            .filter(s => s.length > 0);

        const sendData = {
            ...videoForm,
            tagGenre : genres || null,
            tagActeur : actors || null,
            numberSeason: videoForm.type === "SERIE" ? (parseInt(videoForm.numberSeason) || 0) : undefined
        };

        try {
            // Différenciation des requests selon le type d'ajout
            const request = videoForm.type === "SERIE" ? "/api/serie/add" : "/api/video/add";
            const res = await fetch(request, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(sendData)
            });

            if (!res.ok) throw new Error("Erreur lors de la création de la vidéo");

            alert(`${videoForm.type === "SERIE" ? "Série" : "Vidéo"} créée avec succès !`);
            setVideoForm({
                type: "FILM",
                title: "",
                description: "",
                releaseDate: "",
                imagePath: "",
                tags: "",
                numberSeason: ""
            });
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la création");
        }
    };

    return (
        <div className="admin-panel">
            <h2>Ajouter un Média</h2>
            <form onSubmit={handleVideoSubmit}> {/* Formulaire ajout de vidéo ou série */}
                <label>Type</label>
                <select name="type" value={videoForm.type} onChange={handleVideoChange}>
                    <option value="VIDEO">Film</option>
                    <option value="SERIE">Série</option>
                </select>

                <label>Titre</label>
                <input type="text" name="title" value={videoForm.title} onChange={handleVideoChange} required />

                <label>Description</label>
                <textarea name="description" rows="4" value={videoForm.description} onChange={handleVideoChange} />

                <label>Date de sortie</label>
                <input type="date" name="releaseDate" value={videoForm.releaseDate} onChange={handleVideoChange} />

                <label>Chemin de l'image</label>
                <input type="text" name="imagePath" value={videoForm.imagePath} onChange={handleVideoChange} />

                {videoForm.type === "SERIE" && (
                    <>
                        <label>Nombre de saisons</label>
                        <input type="number" name="numberSeason" value={videoForm.numberSeason} onChange={handleVideoChange} />
                    </>
                )}

                <label>Genres (Saisir le nom des genres, si le genre est inconnu, il sera ajouté à la base de données)</label>
                <p>Genres disponibles : {genreList.map(g => g.genreName).join(", ")}</p>

                <input type="text" name="tags" value={videoForm.tagGenre} onChange={handleVideoChange} />

                <label>Acteurs (Saisir l'acteur n'existe pas, il sera ajouté à la base de données)</label>
                <p>Acteurs disponibles : {actorList.map(a => `${a.firstName} ${a.lastName}`).join(", ")}</p>
                <input type="text" name="actors" value={videoForm.tagActeur} onChange={handleVideoChange} />

                <button type="submit">Créer {videoForm.type === "SERIE" ? "la Série" : "la Vidéo"}</button>
            </form>
        </div>
    );
}