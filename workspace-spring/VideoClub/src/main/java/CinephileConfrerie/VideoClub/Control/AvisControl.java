package CinephileConfrerie.VideoClub.Control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.AvisDao;
import CinephileConfrerie.VideoClub.model.Avis;

/**
 * Controller qui gère l'api REST pour les avis
 */
@RestController
@RequestMapping("/api")
public class AvisControl {

    @Autowired
    private AvisDao avisDao;

    @GetMapping("/avis/{id}")
    public Avis getAvisById(@PathVariable Long id) {
        return avisDao.getAvisById(id);
    }

    @GetMapping("/avis/video/{id}")
    public ResponseEntity<?> getListAvisByVideoId(@PathVariable Long id) {
        List<Avis> listeAvisVideo = avisDao.getListAvisByVideoId(id);
        System.out.println("Liste des avis : " + listeAvisVideo);

        if (listeAvisVideo.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Found " + listeAvisVideo.size() + " review(s)",
                        "reviews", listeAvisVideo));
    }

}