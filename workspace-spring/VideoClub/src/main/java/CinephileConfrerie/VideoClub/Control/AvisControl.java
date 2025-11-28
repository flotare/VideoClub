package CinephileConfrerie.VideoClub.Control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.dao.AvisDao;
import CinephileConfrerie.VideoClub.dao.VideoDao;
import CinephileConfrerie.VideoClub.dto.AvisDTO;
import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Media.Video;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AvisControl {

    @Autowired
    private AvisDao avisDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private AccountDAO accountDAO;

    @GetMapping("/avis/{id}")
    public Avis getAvisById(@PathVariable Long id) {
        return avisDao.getAvisById(id);
    }

    @GetMapping("/avis/video/{id}")
    public ResponseEntity<?> getListAvisByVideoId(@PathVariable Long id) {
        List<Avis> listeAvisVideo = avisDao.getListAvisByVideoId(id);
        System.out.println("Liste des avis : " + listeAvisVideo);

        if (listeAvisVideo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Found " + listeAvisVideo.size() + " review(s)",
                        "reviews", listeAvisVideo));
    }

    @PostMapping("/avis/video")
    public ResponseEntity<?> postAvis(@RequestBody AvisDTO avisDTO) {
        Video video = videoDao.getVideoById(avisDTO.getIdVideo());
        Account account = accountDAO.getByPseudo(avisDTO.getPseudo()).get();
        Avis avis = avisDao.save(video,account,avisDTO.getComment(),avisDTO.getNote());

        return ResponseEntity.ok(
                Map.of(
                        "message", "Avis posté sur la vidéo : " + video.getTitle(),
                        "review", avis.getComment()));
    }
    

}