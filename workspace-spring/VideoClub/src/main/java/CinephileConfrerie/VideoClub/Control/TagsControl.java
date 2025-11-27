package CinephileConfrerie.VideoClub.Control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.TagsDao;
import CinephileConfrerie.VideoClub.model.TagActeur;
import CinephileConfrerie.VideoClub.model.TagGenre;
import CinephileConfrerie.VideoClub.model.Tags;

@RestController
@RequestMapping("/api")
public class TagsControl {

    @Autowired
    private TagsDao tagsDao;

    @GetMapping("/tags")
    public List<Tags> getTagsByType(@RequestParam(required = false) String type) {
        if (type == null) {
            return tagsRepository.findAll();
        }
        return tagsRepository.findByTypeTag(type);
    }

    @GetMapping("/tags/genre/{id}")
    public Tags getGenreById(@PathVariable Long id) {
        return tagsDao.getTagsById(id);
    }

    @GetMapping("/tags/genres")
    public List<Tags> getAllGenres() {
        return tagsDao.getAllGenres();
    }

    @PostMapping("/tags/genre/add")
    public void addGenre(@RequestBody TagGenre genre) {
        tagsDao.getOrCreateActor(genre.getGenreName());
    }

    @DeleteMapping("/tags/genre/{id}")
    public void removeGenre(@PathVariable Long id) {
        tagsDao.deleteTagsById(id);
    }

    @GetMapping("/tags/actor/{id}")
    public Tags getActorById(@PathVariable Long id) {
        return tagsDao.getTagsById(id);
    }

    @GetMapping("/tags/actors")
    public List<Tags> getAllActors() {
        return tagsDao.getAllActors();
    }

    @PostMapping("/tags/actor/add")
    public void addActeur(@RequestBody TagActeur acteur) {
        tagsDao.getOrCreateActor(acteur.getFirstName() + " " + acteur.getLastName());
    }

    @DeleteMapping("/tags/actor/{id}")
    public void removeActeur(@PathVariable Long id) {
        tagsDao.deleteTagsById(id);
    }
}