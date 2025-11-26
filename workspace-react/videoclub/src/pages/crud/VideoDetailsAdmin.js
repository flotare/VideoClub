import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./AjoutElement.css";
import VideoCard from "../../components/VideoCard";

export default function VideoDetailsAdmin() {
    const { id } = useParams();
    const [video, setVideo] = useState(null);
    const navigate = useNavigate();

    function handleClickModify(){
        navigate(`/admin/video/edit/${video.id}`);
    }
    function handleClickBack(){
        navigate(`/admin/video`);
    }


    useEffect(() => {
        fetch(`/api/video/${id}`)
            .then(res => res.json())
            .then(data => setVideo(data))
            .catch(err => console.error(err));
    }, [id]);

    if (!video) return <p>Chargement...</p>;

    return (
        <div className="admin-panel">
            <h2>Détails de la vidéo</h2>

            <p><strong>ID :</strong> {video.id}</p>
            <p><strong>Description :</strong> {video.description}</p>
            <p><strong>Date :</strong> {video.releaseDate}</p>

            <VideoCard current_video={video}></VideoCard>

            <button onCLick={handleClickModify} className="btn-edit">Modifier</button>
            <button onCLick={handleClickBack} className="btn-back">Retour</button>
        </div>
    );
}
