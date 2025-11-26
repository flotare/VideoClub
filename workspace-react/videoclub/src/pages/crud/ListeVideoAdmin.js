import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AjoutElement.css";

export default function ListeVideoAdmin() {
    const [videos, setVideos] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch("/api/videos")
            .then(res => res.json())
            .then(data => setVideos(data))
            .catch(err => console.error(err));
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm("Supprimer cette vidéo ?")) return;

        try {
            const res = await fetch(`/api/video/${id}`, { method: "DELETE" });

            if (!res.ok) throw new Error("Erreur suppression");

            setVideos(videos.filter(v => v.id !== id));
        } catch (err) {
            alert("Erreur lors de la suppression");
            console.error(err);
        }
    };


    return (
        <div className="admin-panel">
            <h2>Gestion des Vidéos</h2>

            <button className="btn-add" onClick={() => navigate("/admin/video/add")}>
                ➕ Ajouter une vidéo
            </button>

            <table className="crud-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titre</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {videos.map(video => (
                        <tr key={video.idVideo}>
                            <td>{video.idVideo}</td>
                            <td>{video.title}</td>
                            <td>{video.releaseDate}</td>
                            <td>
                                <div style={{ display: 'inline-flex', gap: '6px' }}>
                                    <button
                                        className="btn-edit"
                                        onClick={() => navigate(`/admin/video/edit/${video.idVideo}`)}
                                    >
                                        Modifier
                                    </button>
                                    <button
                                        className="btn-delete"
                                        onClick={() => handleDelete(video.idVideo)}
                                    >
                                        Supprimer
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
