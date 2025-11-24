import "./VideoDetails.css";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";


function VideoDetails() {
    const { id } = useParams(); // Récupération de l'id de la vidéo
    const [video, setVideo] = useState(null);
    const [hover, setHover] = useState(0); // Note temporaire lors du hover
    const [note, setNote] = useState(0); // Note choisie

    function handleClickChangeNote(idNote) {
        setNote(prev => prev === idNote ? 0 : idNote);
    }

    function handleMouseEnter(id) {
        setHover(id);
    }

    function handleMouseLeave() {
        setHover(0);
    }

    function handleSubmit(){

    }

    useEffect(() => {
        fetch(`/video/${id}`)
            .then(res => res.json())
            .then(data => {
                console.log("Response from backend:", data);
                setVideo(data);
            })
            .catch(err => console.log(err.message));
    }, [id]);


    if (!video) return <p className="waiting">Chargement...</p>;

    // Obtention de la liste des acteurs en filtrant sur les attributs du tag 
    const actors = video.tagList
        .filter(tag => tag.firstName)
        .map(tag => `${tag.firstName} ${tag.lastName}`)
        .join(", ");


    const genres = video.tagList
        .filter(tag => tag.genreName)
        .map(tag => tag.genreName)
        .join(", ");

    return ( // Détails de la vidéo à afficher
        <div className="video-details">
            <div className="video-details-top">
                <img
                    className="video-details-image"
                    src={video.imagePath}
                    alt={`Affiche du film "${video.title}" `}
                />
                <div className="video-details-info">
                    <h1>{video.title}</h1>
                    <p><strong>Date de sortie:</strong> {video.releaseDate}</p>

                    {/* Affichage conditionnel */}
                    {video.episodeNumber && (
                        <p><strong>Numéro d'épisode:</strong> {video.episodeNumber}</p>
                    )}

                    <p className="video-details-description">{video.description}</p>

                    {video.tagList && video.tagList.length > 0 && (
                        <div className="video-details-tags">
                            {actors && <p><strong>Acteurs :</strong> {actors}</p>}
                            {genres && <p><strong>Genres :</strong> {genres}</p>}
                        </div>
                    )}

                    <div className="video-details-buttons">
                        <button>Ajouter à une playlist</button>
                        {video.previousVideo && (
                            <button>Vidéo précédente</button>
                        )}
                        {video.nextVideo && (
                            <button>Vidéo suivante</button>
                        )}

                    </div>
                </div>
            </div>

            <div className="video-review">
                <div className="video-review-add-review-form">
                    <form method="post" onSubmit={handleSubmit}>
                        <div className="video-review-comment-section">
                            <h2 className="video-review-comment-title">Laisser un avis</h2>
                            <div className="video-review-comment-container">
                                {/* Photo de profil */}
                                <img
                                    className="video-review-comment-avatar"
                                    src="/default-avatar.jpg"
                                    alt="avatar"
                                />
                                {/* Note */}
                                <div className="video-review-form-rating">
                                    {[1, 2, 3, 4, 5].map((id) => (
                                        <span
                                            key={id}
                                            className={
                                                (hover || note) >= id ? "star filled" : "star"
                                            }
                                            onClick={() => handleClickChangeNote(id)}
                                            onMouseEnter={() => handleMouseEnter(id)}
                                            onMouseLeave={handleMouseLeave}
                                        >
                                            ★
                                        </span>
                                    ))}
                                </div>
                                {/* Commentaire */}
                                <div className="video-review-comment-body">
                                    <textarea
                                        className="video-review-comment-textarea"
                                        placeholder="Écris ton commentaire ici..."
                                    />
                                    <div className="video-review-comment-footer">
                                        <span className="char-counter">0 / 255</span>
                                        <button className="comment-btn" type="submit">Envoyer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default VideoDetails;