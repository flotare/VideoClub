package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.model.Media.Video;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Controller that manages the individual pages of the videos
 */
@RestController
public class VideoControl {

    @GetMapping(value = "/video/{id}")
    public Video sendVideo(@PathVariable int id) {
        return new Video();
    }
    
}
