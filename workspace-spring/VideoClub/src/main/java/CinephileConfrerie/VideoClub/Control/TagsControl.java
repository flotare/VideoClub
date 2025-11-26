package CinephileConfrerie.VideoClub.Control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CinephileConfrerie.VideoClub.dao.TagsDao;
import CinephileConfrerie.VideoClub.model.Tags;


@RestController
@RequestMapping("/api")
public class TagsControl {

    @Autowired
    private TagsDao tagsDao;

    @GetMapping("/tags/genres")
    public List<Tags> getAllGenres(){
        return tagsDao.getAllGenres();
    }

    @PostMapping("/tags/genres/add")
    public void addGenre(@RequestBody Tags genre){

    }

    @GetMapping("/tags/actors")
    public List<Tags> getAllActors(){
        return tagsDao.getAllActors();
    }

    @PostMapping("/tags/actors/add")
    public void addActeur(@RequestBody Tags acteur){

    }
}
