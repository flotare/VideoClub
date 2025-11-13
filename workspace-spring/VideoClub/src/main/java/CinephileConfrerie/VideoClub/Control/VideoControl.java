package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.model.Media.Video;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller that manages the individual pages of the videos
 */
@RestController
public class VideoControl {

    @RequestMapping(value = "/video/{id}", method=RequestMethod.GET)
    public Video sendVideo(@PathVariable Video video) {
        return new Video();
    }
    
}
