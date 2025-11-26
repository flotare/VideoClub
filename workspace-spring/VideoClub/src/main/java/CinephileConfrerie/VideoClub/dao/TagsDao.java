package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Tags;

@Service
public class TagsDao {

    @Autowired
    private TagsRepository tagsRepository;

    public Tags getTagsById(Long id){
        return tagsRepository.findById(id).orElse(null);
    }

    public List<Tags> getTagsById(List<Long> ids){
        return tagsRepository.findAllById(ids);
    }

    public List<Tags> getAllActors(){
        return tagsRepository.findAllActors();
    }

    public List<Tags> getAllGenres(){
        return tagsRepository.findAllGenres();
    }

}
