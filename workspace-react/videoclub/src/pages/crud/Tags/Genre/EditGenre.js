import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../../AjoutElement.css";

export default function EditGenre() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [genreForm, setGenreForm] = useState(null);

    useEffect(() => {
        fetch(`/api/tags/genre/${id}`)
            .then(res => res.json())
            .then(data => setGenreForm(data))
            .catch(err => console.error(err));
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setGenreForm({ ...genreForm, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await fetch(`/api/tags/genre/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(genreForm)
            });

            if (!res.ok) throw new Error("Erreur update");

            alert("Genre modifié !");
            navigate("/admin/genre");
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la modification");
        }
    };

    if (!genreForm) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Modifier un Genre</h2>

            <form onSubmit={handleSubmit}>
                <label>Nom du Genre</label>
                <input type="text" name="genreName" value={genreForm.genreName || ""} onChange={handleChange} />

                <button className="btn-add-element" type="submit">Sauvegarder</button>
            </form>
        </div>
    );
}