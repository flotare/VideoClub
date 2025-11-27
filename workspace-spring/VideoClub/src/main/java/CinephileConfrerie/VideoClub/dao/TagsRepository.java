package CinephileConfrerie.VideoClub.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CinephileConfrerie.VideoClub.model.TagActeur;
import CinephileConfrerie.VideoClub.model.TagGenre;
import CinephileConfrerie.VideoClub.model.Tags;
import java.util.List;

public interface TagsRepository extends JpaRepository<Tags,Long>{

    @Query("SELECT a FROM Tags a WHERE TYPE(a) = TagActeur ")
    public List<Tags> findAllActors();

    @Query("SELECT g FROM Tags g WHERE TYPE(g) = TagGenre ")
    public List<Tags> findAllGenres();

    Optional<TagGenre> findByGenreName(String genreName);
    Optional<TagActeur> findByFirstNameAndLastName(String firstName, String lastName);
}
public interface TagsRepository extends JpaRepository<Tags, Long> {
    List<Tags> findByTypeTag(String typeTag);
}

