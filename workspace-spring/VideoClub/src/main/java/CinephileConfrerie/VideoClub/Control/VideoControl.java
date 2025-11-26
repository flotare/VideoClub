package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.VideoDao;
import CinephileConfrerie.VideoClub.dto.VideoDTO;
import CinephileConfrerie.VideoClub.model.Media.Video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Controller qui gère l'api REST pour les vidéos
 */

@RestController
@RequestMapping("/api")
public class VideoControl {


    @Autowired
    private VideoDao videoDao;

    @GetMapping(value = "/video/{id}")
    public Video showVideoDetail(@PathVariable Long id) {
        Video video = videoDao.getVideoById(id);
        return video;
    }

    @GetMapping(value = "/videos")
    public List<Video> showVideoList(@RequestParam(required = false) String type){

        if ("films".equalsIgnoreCase(type)) {
            return videoDao.getAllMovies();  // films
        }

        if ("series".equalsIgnoreCase(type)) {
            return videoDao.getAllSeries();
        }

        return videoDao.getAllVideos();
    }
    

    @PostMapping(value = "/video/add")
    public ResponseEntity<?> addVideoToDatabase(@RequestBody VideoDTO videoDTO){
        videoDao.addVideo(videoDTO);
        return ResponseEntity.ok("Video created");
    }

    @PutMapping(value = "/video/{id}")
    public void modifyVideo(@RequestBody VideoDTO videoDTO){
        
    }

    @DeleteMapping(value = "/video/{id}")
    public void removeVideo(@PathVariable Long id){
        videoDao.deleteVideoById(id);
    }
}