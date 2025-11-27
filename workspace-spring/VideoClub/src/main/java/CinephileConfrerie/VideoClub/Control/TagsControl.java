package CinephileConfrerie.VideoClub.Control;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import CinephileConfrerie.VideoClub.dao.TagsRepository;
import CinephileConfrerie.VideoClub.model.Tags;

@RestController
public class TagsControl {

    private final TagsRepository tagsRepository;

    public TagsControl(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    // Endpoint pour récupérer les tags filtrés par type
    @GetMapping("/tags")
    public List<Tags> getTagsByType(@RequestParam(required = false) String type) {
        if (type == null) {
            return tagsRepository.findAll();
        }
        return tagsRepository.findByTypeTag(type);
    }
}
