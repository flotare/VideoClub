import './ListeVideo.css';
import Video from './VideoCard';
import { useState, useEffect } from 'react';

function ListeVideo() {

    const [videos,setVideos] = useState([]);
    const [type, setType] = useState('films'); // par défaut, on affiche les films

    // GET type de vidéo ( "films" / "series" )
    useEffect(() => {
        fetch(`/api/videos?type=${type}`)
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setVideos(data);
            })
            .catch((err) => {
                console.log(err.message);
            });
    }, [type]); // Ne s'active uniquement que si type change de valeur

    return (
        <div className="video-list-container">
            <div className="buttons-container">
                <button onClick={() => setType('films')}>Films</button>
                <button onClick={() => setType('series')}>Séries</button>
            </div>
            
            <ul className="video-list">
                {videos.map(video => (
                        <Video key={video.idVideo} current_video={video}/>
                    ))  
                }
            </ul>
        </div>)
}
export default ListeVideo;