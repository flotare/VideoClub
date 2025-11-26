import { useState } from "react";
import "./AjoutElement.css";

export default function AjoutElementTagsGenre() {

    // ------------------- STATE FORMULAIRE GENRE -------------------
    const [genreForm, setGenreForm] = useState({ genreName: "" });

    // ------------------- HANDLERS GENRE -------------------
    const handleGenreChange = (e) => {
        setGenreForm({ genreName: e.target.value });
    };

    const handleGenreSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await fetch("/api/tags/genre/add", {
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

    return (
        <div className="admin-panel"> {/* FORMULAIRE AJOUT TAG GENRE */}
            <h2>Ajouter un Tag (Genre)</h2>
            <form onSubmit={handleGenreSubmit}>
                <label>Nom du genre</label>
                <input type="text" value={genreForm.genreName} onChange={handleGenreChange} required />
                <button type="submit">Créer le tag genre</button>
            </form>
        </div>
    )
}