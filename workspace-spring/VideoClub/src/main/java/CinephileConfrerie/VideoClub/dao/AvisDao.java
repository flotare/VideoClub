package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Media.Serie;
import CinephileConfrerie.VideoClub.model.Media.Video;

@Service
public class AvisDao {
    @Autowired
    private AvisRepository avisRepository;

    public Avis getAvisById(Long id){
        return avisRepository.findById(id).orElse(null);
    }

    public List<Avis> getListAvisByVideoId(Long idVideo){        
        return avisRepository.findByAvisVideo_IdVideo(idVideo);
    }

    public Avis save(Video video, Account account, String comment, Integer note){
        Avis avis = new Avis();
        avis.setAvisAccount(account);
        if (video.getClass() == Serie.class){
            avis.setAvisSerie((Serie)video);
        } else {
            avis.setAvisVideo(video);
        }
        avis.setComment(comment);
        avis.setNote(note);
        return avisRepository.save(avis);
    }
}