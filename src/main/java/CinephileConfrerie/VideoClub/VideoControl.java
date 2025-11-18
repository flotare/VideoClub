package CinephileConfrerie.VideoClub;

import CinephileConfrerie.VideoClub.model.Media.Video;
import CinephileConfrerie.VideoClub.repository.VideoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 
public class VideoControl {

    @Autowired
    private VideoRepository videoRepository; 

    @GetMapping("/videos")
    public List<Video> listeVideos() {
        return videoRepository.findAll(); 
    }
}