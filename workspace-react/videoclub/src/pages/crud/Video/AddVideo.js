import { useEffect, useState } from "react";
import "../AjoutElement.css";
import MultiTagInput from "../../../components/MultiTagInput";

export default function AjoutElementVideo() {
    // ------------------- STATE FORMULAIRE VIDEO -------------------

    const [videoForm, setVideoForm] = useState({
        type: "FILM",
        title: "",
        description: "",
        releaseDate: "",
        imagePath: "",
        tagGenre: [],
        tagActeur: [],
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
        const genres = videoForm.tagGenre;
        const actors = videoForm.tagActeur;

        const sendData = {
            ...videoForm,
            tagGenre: genres || null,
            tagActeur: actors || null,
            numberSeason: videoForm.type === "SERIE" ? (parseInt(videoForm.numberSeason) || 0) : undefined
        };

        try {
                const res = await fetch("/api/video/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(sendData)
            });

            if (!res.ok) {
                const errMsg = await res.text();
                throw new Error(errMsg || "Erreur lors de l'ajout");
            }

            alert(`${videoForm.type === "SERIE" ? "Série" : "Vidéo"} créée avec succès !`);

            setVideoForm({
                type: "FILM",
                title: "",
                description: "",
                releaseDate: "",
                imagePath: "",
                tagGenre: [],
                tagActeur: [],
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


                <MultiTagInput
                    label="Genres (Saisir le nom des genres, si le genre est inconnu, il sera ajouté à la base de données)  "
                    value={videoForm.tagGenre}
                    onChange={(val) => setVideoForm({ ...videoForm, tagGenre: val })}
                    suggestions={genreList.map(g => g.genreName)}
                />


                <MultiTagInput
                    label="Acteurs (Saisir l'acteur n'existe pas, il sera ajouté à la base de données)"
                    value={videoForm.tagActeur}
                    onChange={(val) => setVideoForm({ ...videoForm, tagActeur: val })}
                    suggestions={actorList.map(a => {
                        const fullName = [a.firstName, a.lastName]
                            .filter(part => part && part !== "null" && part !== "undefined")
                            .join(" ");
                        return fullName;
                    })}
                />

                <button className="btn-validate" type="submit">Créer {videoForm.type === "SERIE" ? "la Série" : "la Vidéo"}</button>
            </form>
        </div>
    );
}