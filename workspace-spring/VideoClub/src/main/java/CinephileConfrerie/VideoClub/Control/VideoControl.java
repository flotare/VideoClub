package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.VideoDao;
import CinephileConfrerie.VideoClub.dto.VideoDTO;
import CinephileConfrerie.VideoClub.model.Media.Video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Controller qui gère l'api REST pour les vidéos
 */

@RestController
public class VideoControl {

    @Autowired
    private VideoDao videoDao;

    @GetMapping(value = "/video/{id}")
    public Video getVideoDetail(@PathVariable Long id) {
        Video video = videoDao.getVideoById(id);
        return video;
    }

    @GetMapping(value = "/videos")
    public List<Video> showVideoList(@RequestParam(required = false) String type){

        if ("films".equalsIgnoreCase(type)) {
            return videoDao.getAllMovies();
        }

        if ("series".equalsIgnoreCase(type)) {
            return videoDao.getAllSeries();
        }

        return videoDao.getAllVideos();
    }
    

    @PostMapping(value = "/api/video/add")
    public ResponseEntity<?> addVideoToDatabase(@RequestBody VideoDTO videoDTO){
        Video v = videoDao.addVideo(videoDTO);
        return (v!=null) ? 
            ResponseEntity.status(HttpStatus.CREATED).body("Video created successfully") : 
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has occured while creating the video");
    }

    @PutMapping(value = "/api/video/modify/{id}")
    public ResponseEntity<?> modifyVideo(@RequestBody VideoDTO videoDTO, @PathVariable Long id){
        Video v = videoDao.saveOrUpdate(videoDTO,id);
        return (v!=null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body("Video modified successfully") : 
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has occured while creating the video");
    }

    @DeleteMapping(value = "/api/video/delete/{id}")
    public void removeVideo(@PathVariable Long id){
        videoDao.deleteVideoById(id);
    }
}