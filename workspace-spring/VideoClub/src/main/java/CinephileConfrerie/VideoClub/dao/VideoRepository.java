package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CinephileConfrerie.VideoClub.model.Media.Video;

public interface VideoRepository extends JpaRepository<Video,Long>{


    @Query("SELECT v FROM Video v WHERE TYPE(v) = Video ")
    public List<Video> findAllMovies();

    @Query("SELECT v FROM Video v WHERE TYPE(v) = Serie ")
    public List<Video> findAllSeries();

    List<Video> findByTagList_IdTag(Long idTag);
}
