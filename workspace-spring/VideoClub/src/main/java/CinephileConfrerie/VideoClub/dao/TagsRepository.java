package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CinephileConfrerie.VideoClub.model.Tags;

public interface TagsRepository extends JpaRepository<Tags,Long>{

    @Query("SELECT a FROM Tags a WHERE TYPE(a) = TagActeur ")
    public List<Tags> findAllActors();

    @Query("SELECT g FROM Tags g WHERE TYPE(g) = TagGenre ")
    public List<Tags> findAllGenres();
}