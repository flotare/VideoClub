import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../AjoutElement.css";
import VideoCard from "../../../components/VideoCard";

export default function VideoDetailsAdmin() {
    const { id } = useParams();
    const [video, setVideo] = useState(null);
    const navigate = useNavigate();


    useEffect(() => {
        fetch(`/video/${id}`)
            .then(res => res.json())
            .then(data => setVideo(data))
            .catch(err => console.error(err));
    }, [id]);

    if (!video) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Détails de la vidéo</h2>

            <button onClick={() => navigate(`/admin/video/edit/${video.idVideo}`)} className="btn-edit">Modifier</button>
            <button onClick={() => navigate(`/admin/video`)} className="btn-back">Retour</button>

            <p><strong>Description :</strong> {video.description || "Aucune description renseignée"}</p>
            <p><strong>Acteurs : </strong>
                {video.tagList
                    ?.filter(t => t.firstName)
                    .map(t => `${t.firstName} ${t.lastName}`)
                    .join(", ")
                || "Aucun acteur renseigné"}</p>
            <p><strong>Genres : </strong>
                {video.tagList
                    .filter(tag => tag.genreName)
                    .map(tag => tag.genreName)
                    .join(", ")
                || "Aucun genre renseigné"}</p>

            <p><strong>Date :</strong> {video.releaseDate || "Aucune date renseignée"}</p>

            <VideoCard current_video={video}></VideoCard>
        </div>
    );
}
