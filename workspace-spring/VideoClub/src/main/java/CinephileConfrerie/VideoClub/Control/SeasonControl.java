package CinephileConfrerie.VideoClub.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.SeasonDao;
import CinephileConfrerie.VideoClub.dto.SeasonDTO;

@RestController
@RequestMapping("/api")
public class SeasonControl {


    @Autowired
    private SeasonDao seasonDao;


    @PostMapping
    public ResponseEntity<?> addSeason(@RequestBody SeasonDTO seasonDTO){
        boolean success = seasonDao.addSeason(seasonDTO);
        return success ? ResponseEntity.ok("Season created") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to create season");
    }
}
