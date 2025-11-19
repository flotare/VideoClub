package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Media.Video;

@Service
public class VideoDao {


    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    public List<Video> getAllMovies(){
        return videoRepository.findAllMovies();
    }

    public List<Video> getAllSeries(){
        return videoRepository.findAllSeries();
    }

    public Video saveOrUpdate(Video video) {
        return videoRepository.save(video);
    }

    public void deleteTodoById(Long id) {
        videoRepository.deleteById(id);
    }

}
