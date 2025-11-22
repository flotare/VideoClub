import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";


function VideoDetails() {
    const { id } = useParams(); // Récupération de l'id de la vidéo
    const [video, setVideo] = useState(null);

    useEffect(() => {
        fetch(`/video/${id}`)
            .then(res => res.json())
            .then(data => setVideo(data))
            .catch(err => console.log(err.message));
    }, [id]);

    if (!video) return <p>Chargement...</p>; 

    return ( // Détails de la vidéo à afficher
        <div className="video-details"> {/** Infos vidéo */}
            <h1 className="video-details-title">{video.title}</h1>
            <img className="video-details-image" src={video.imagePath} alt="Affiche du film" />
            <p className="video-details-description">{video.description}</p>
            <p className="video-details-episode-number"></p> {/** Optionnel */}

            <div className="video-details-buttons">
                <button></button> {/** Ajouter Playlist */}
                <button></button> {/** Optionnel : vidéo préc */}
                <button></button> {/** Optionnel : vidéo suiv */}
            </div>
 
            <div className="movie-review"> {/** Reviews */}

            </div>
        </div>
    )
}

export default VideoDetails;