import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../AjoutElement.css";
import MultiTagInput from "../../../components/MultiTagInput";

export default function EditVideo() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [videoForm, setVideoForm] = useState(null);
    const [genreList, setGenreList] = useState([]);
    const [actorList, setActorList] = useState([]);

    useEffect(() => {
        fetch(`/video/${id}`)
            .then(res => res.json())
            .then(data => {
                // Séparer tags en genres et acteurs
                const tagGenre = data.tagList
                    .filter(tag => tag.genreName)
                    .map(tag => tag.genreName);

                const tagActeur = data.tagList
                    .filter(tag => tag.firstName)
                    .map(tag => `${tag.firstName} ${tag.lastName}`);

                setVideoForm({ ...data, tagGenre, tagActeur });
            })
            .catch(err => console.error(err));
    }, [id]);

    useEffect(() => {
        fetch(`/api/tags/genres`)
            .then(res => res.json())
            .then(data => setGenreList(data))
            .catch(err => console.error(err));
    }, []);

    useEffect(() => {
        fetch(`/api/tags/actors`)
            .then(res => res.json())
            .then(data => setActorList(data))
            .catch(err => console.error(err));
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVideoForm({ ...videoForm, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const sendData = {
            ...videoForm,
            tagGenre: videoForm.tagGenre,
            tagActeur: videoForm.tagActeur
        };


        try {
            const res = await fetch(`/api/video/modify/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(sendData)
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
                <input type="text" name="title" value={videoForm.title} onChange={handleChange} required />

                <label>Description</label>
                <textarea name="description" rows="4" value={videoForm.description} onChange={handleChange} />

                <label>Date de sortie</label>
                <input type="date" name="releaseDate" value={videoForm.releaseDate} onChange={handleChange} />

                <label>Image</label>
                <input type="text" name="imagePath" value={videoForm.imagePath} onChange={handleChange} />

                <MultiTagInput
                    label="Genres"
                    value={videoForm.tagGenre}
                    onChange={(val) => setVideoForm({ ...videoForm, tagGenre: val })}
                    suggestions={genreList.map(g => g.genreName)}
                />

                <MultiTagInput
                    label="Acteurs"
                    value={videoForm.tagActeur}
                    onChange={(val) => setVideoForm({ ...videoForm, tagActeur: val })}
                    suggestions={actorList.map(a => `${a.firstName} ${a.lastName}`)}
                />

                <button className="btn-validate" type="submit">Sauvegarder</button>
            </form>
        </div>
    );
}
