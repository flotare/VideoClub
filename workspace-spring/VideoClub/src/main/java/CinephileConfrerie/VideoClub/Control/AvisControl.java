package CinephileConfrerie.VideoClub.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.AvisDao;


/**
 * Controller qui gère l'api REST pour les avis
 */
@RestController
public class AvisControl {

    private static final String DEFAULT_ACCOUNT_IMAGE_PATH = "./assets/default_user_icon.png";

    @Autowired
    private AvisDao AvisDao;



}
