package CinephileConfrerie.VideoClub.Control;

import org.springframework.beans.factory.annotation.Autowired;
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
    private AvisDao AvisDao;

    @GetMapping("/avis/{id}")
    public Avis getAvisById(@PathVariable Long id){
        return AvisDao.getAvisById(id);
    }


}
