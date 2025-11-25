package CinephileConfrerie.VideoClub.Control;

import CinephileConfrerie.VideoClub.model.Media.Video;
import CinephileConfrerie.VideoClub.service.VideoService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.ResponseEntity; 

@RestController
@RequestMapping("/api/videos")
public class VideoRestController { 

  
    @Autowired
    private VideoService videoService;

    @GetMapping // Mappe GET /api/videos
    public List<Video> getVideos(@RequestParam(required = false) String title) {
        // Le service gère la logique de renvoyer tout si 'title' est null/vide.
        return videoService.searchVideosByTitle(title);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Video> sendVideo(@PathVariable Long id) { // Utilisation de Long pour l'ID
        return videoService.getVideoById(id)
                .map(ResponseEntity::ok)         // Statut 200 OK si trouvé
                .orElse(ResponseEntity.notFound().build()); // Statut 404 NOT FOUND sinon
    }

}