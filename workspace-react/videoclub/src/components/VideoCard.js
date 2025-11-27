import "./VideoCard.css";
import { useNavigate } from 'react-router-dom';
import default_video_image from "../../src/assets/default_video.png";

function VideoCard({ current_video }) {
   const navigate = useNavigate();
   const id = "video-" + current_video.idVideo;

   return (
      <li id={id} className="video-card" onClick={() => navigate(`/video/${current_video.idVideo}`)}>
         <img src={current_video.imagePath ? current_video.imagePath : default_video_image} 
            alt={`Affiche du film : ${current_video.title}`} ></img>
         <p>{current_video.title}</p>
      </li>
   );
}  

export default VideoCard;