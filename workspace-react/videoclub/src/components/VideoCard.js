import "./VideoCard.css";
import { useNavigate } from 'react-router-dom';

function VideoCard({ current_video }) {
   const navigate = useNavigate();
   const id = "video-" + current_video.idVideo;
   //const resize = "?w=400&h=1000&c=pad";
   const resize = "";

   function handleClick() {
      navigate(`/video/${current_video.idVideo}`);
   }

   return (
      <li id={id} className="video-card" onClick={handleClick}>
         <img src={`${current_video.imagePath}${resize}`} alt={`Affiche du film : ${current_video.title}`} ></img>
         <p>{current_video.title}</p>
      </li>
   );
}

export default VideoCard;