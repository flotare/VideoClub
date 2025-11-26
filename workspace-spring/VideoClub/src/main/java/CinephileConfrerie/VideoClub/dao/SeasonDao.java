package CinephileConfrerie.VideoClub.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.dto.SeasonDTO;
import CinephileConfrerie.VideoClub.model.Media.Season;

@Service
public class SeasonDao {

    @Autowired
    private SeasonRepository seasonRepository;

    public Season getSeasonById(Long id){
        return seasonRepository.findById(id).orElse(null);
    }

    public boolean addSeason(SeasonDTO seasonDTO){
        return false;
    }
}
