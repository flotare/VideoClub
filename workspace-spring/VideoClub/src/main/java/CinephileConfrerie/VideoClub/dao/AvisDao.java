package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Avis;

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
}
