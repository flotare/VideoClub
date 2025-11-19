import "./Video.css";
import { useState } from 'react';

function Video({current_video}) {
    const [video,setVideo] = useState(current_video);
    const id = "video-" + video.idVideo;

    function handleClick(e) {
    fetch('http://127.0.0.1:8080/video/' + video.idVideo)
       .then((res) => res.json())
       .then((data) => {
          console.log(data);
          setVideo(data);         
       })
       .catch((err) => {
          console.log(err.message);
       });    
    }

  return (
    <li id={id} className="App-body-image" onClick={handleClick}>
      <img src={video.imagePath} alt="Affiche d'un film" ></img>
      <p>{video.title}</p>
   </li>
  );
}

export default Video;