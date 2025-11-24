package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Tags;

@Service
public class TagsDao {

    private TagsRepository tagsRepository;

    public Tags getTagsById(Long id){
        return tagsRepository.findById(id).orElse(null);
    }

    public List<Tags> getTagsById(List<Long> ids){
        return tagsRepository.findAllById(ids);
    }

}
