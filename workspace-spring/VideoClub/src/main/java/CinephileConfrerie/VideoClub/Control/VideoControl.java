package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.VideoDao;
import CinephileConfrerie.VideoClub.model.Media.Video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Controller that manages the individual pages of the videos
 */
@RestController
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
    
}
