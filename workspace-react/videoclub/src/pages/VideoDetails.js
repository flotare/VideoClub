import default_user_icon from "../assets/default_user_icon.png";
import "./VideoDetails.css";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";


function VideoDetails() {
    const { id } = useParams(); // Récupération de l'id de la vidéo
    const [video, setVideo] = useState(null);
    const [hover, setHover] = useState(0); // Note temporaire lors du hover
    const [note, setNote] = useState(0); // Note choisie
    const [comment, setComment] = useState("");
    const [listAvis, setListAvis] = useState([]);


    function handleClickChangeNote(idNote) {
        setNote(prev => prev === idNote ? 0 : idNote);
    }

    function handleMouseEnter(id) {
        setHover(id);
    }

    function handleMouseLeave() {
        setHover(0);
    }

    function handleSubmit() {

    }

    useEffect(() => {
        fetch(`/api/video/${id}`)
            .then(res => res.json())
            .then(data => {
                setVideo(data);
            })
            .catch(err => console.log(err.message));
    }, [id]);

    useEffect(() => {
        fetch(`/api/avis/video/${id}`)
            .then(res => res.json())
            .then(data => {
                setListAvis(data.reviews);
                console.log(data.message);
            })
            .catch(err => console.log(err.message));
    }, [id])


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
                    <button className="video-details-buttons">Ajouter à une playlist</button>
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
                                    src={default_user_icon}
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
                                        maxLength={255}
                                        value={comment}
                                        onChange={(e) => setComment(e.target.value)}
                                    />
                                    <div className="video-review-comment-footer">
                                        <span className="video-review-char-counter">
                                            {comment.length} / 255
                                        </span>
                                        <button className="comment-btn" type="submit">Envoyer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                {/* ===================== */}
                {/*     Autres reviews    */}
                {/* ===================== */}
                <div className="video-review-list">
                    <h2 className="review-list-title">Avis des utilisateurs</h2>

                    {listAvis.length === 0 && (
                        <p className="no-review">Aucun avis pour le moment.</p>
                    )}

                    {listAvis.length > 0 && (listAvis.map((avis) => (
                        <div key={avis.id} className="review-card">
                            <img
                                src={default_user_icon}
                                alt="avatar"
                                className="review-avatar"
                            />

                            <div className="review-content">
                                <p className="review-author">
                                    {avis.username || "Utilisateur anonyme"}
                                </p>

                                {/* Note */}
                                <div className="review-rating">
                                    {[1, 2, 3, 4, 5].map((i) => (
                                        <span
                                            key={i}
                                            className={i <= avis.note ? "star filled" : "star"}
                                        >
                                            ★
                                        </span>
                                    ))}
                                </div>

                                {/* Commentaire (optionnel) */}
                                {avis.comment && (
                                    <p className="review-text">{avis.comment}</p>
                                )}
                            </div>
                        </div>
                    )))}
                </div>
            </div>
        </div>
    )
}

export default VideoDetails;