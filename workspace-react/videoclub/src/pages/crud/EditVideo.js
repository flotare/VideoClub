import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./AjoutElement.css";

export default function EditVideo() {

    const { id } = useParams();
    const navigate = useNavigate();
    const [videoForm, setVideoForm] = useState(null);

    useEffect(() => {
        fetch(`/api/video/${id}`)
            .then(res => res.json())
            .then(data => setVideoForm(data))
            .catch(err => console.error(err));
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVideoForm({ ...videoForm, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await fetch(`/api/video/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(videoForm)
            });

            if (!res.ok) throw new Error("Erreur update");

            alert("Vidéo modifiée !");
            navigate("/admin/video");
        } catch (err) {
            console.error(err);
            alert("Erreur lors de la modification");
        }
    };

    if (!videoForm) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Modifier la Vidéo</h2>

            <form onSubmit={handleSubmit}>

                <label>Titre</label>
                <input type="text" name="title" value={videoForm.title} onChange={handleChange} />

                <label>Description</label>
                <textarea name="description" rows="4" value={videoForm.description} onChange={handleChange} />

                <label>Date de sortie</label>
                <input type="date" name="releaseDate" value={videoForm.releaseDate} onChange={handleChange} />

                <label>Image</label>
                <input type="text" name="imagePath" value={videoForm.imagePath} onChange={handleChange} />

                <label>Tags</label>
                <input type="text" name="tags" value={videoForm.tagList} onChange={handleChange} />

                <button type="submit">Sauvegarder</button>
            </form>
        </div>
    );
}
