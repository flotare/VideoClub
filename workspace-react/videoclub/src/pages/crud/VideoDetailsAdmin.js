import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import "./AjoutElement.css";

export default function VideoDetailsAdmin() {
    const { id } = useParams();
    const [video, setVideo] = useState(null);

    useEffect(() => {
        fetch(`/api/videos/${id}`)
            .then(res => res.json())
            .then(data => setVideo(data))
            .catch(err => console.error(err));
    }, [id]);

    if (!video) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Détails de la vidéo</h2>

            <p><strong>ID :</strong> {video.id}</p>
            <p><strong>Titre :</strong> {video.title}</p>
            <p><strong>Description :</strong> {video.description}</p>
            <p><strong>Date :</strong> {video.releaseDate}</p>

            {video.imagePath && (
                <img src={video.imagePath} alt={video.title} className="detail-img" />
            )}

            <Link to={`/admin/video/edit/${video.id}`} className="btn-edit">Modifier</Link>
            <Link to="/admin/video" className="btn-back">Retour</Link>
        </div>
    );
}
