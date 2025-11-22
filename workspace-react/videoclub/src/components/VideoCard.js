import "./VideoCard.css";
import { useNavigate } from 'react-router-dom';

function VideoCard({ current_video }) {
   const navigate = useNavigate();
   const id = "video-" + current_video.idVideo;

   function handleClick() {
      navigate(`/video/${current_video.idVideo}`);
   }

   return (
      <li id={id} className="App-body-image" onClick={handleClick}>
         <img src={current_video.imagePath} alt="Affiche d'un film" ></img>
         <p>{current_video.title}</p>
      </li>
   );
}

export default VideoCard;