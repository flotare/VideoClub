package CinephileConfrerie.VideoClub.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.dto.VideoDTO;
import CinephileConfrerie.VideoClub.model.Tags;
import CinephileConfrerie.VideoClub.model.Media.Season;
import CinephileConfrerie.VideoClub.model.Media.Video;

@Service
public class VideoDao {

    private static final String DEFAULT_VIDEO_IMAGE_PATH = "./assets/default_video.png";
    private static final String DEFAULT_VIDEO_DESCRIPTION = "Aucune description disponible pour cette vidéo";

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private TagsDao tagsDao;


    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    public List<Video> getAllMovies() {
        return videoRepository.findAllMovies();
    }

    public List<Video> getAllSeries() {
        return videoRepository.findAllSeries();
    }

    public Video saveOrUpdate(Video video) {
        return videoRepository.save(video);
    }

    public void deleteTodoById(Long id) {
        videoRepository.deleteById(id);
    }

    public void addVideo(VideoDTO videoDTO) {
        Video video = new Video();

        video.setTitle(videoDTO.title);

        if(videoDTO.description != null){video.setDescription(videoDTO.description);}
        else{video.setDescription(DEFAULT_VIDEO_DESCRIPTION);}
        
        if(videoDTO.releaseDate != null){video.setReleaseDate(videoDTO.releaseDate);}
        else{video.setReleaseDate(null);}

        if(videoDTO.imagePath != null){video.setImagePath(videoDTO.imagePath);}
        else{video.setImagePath(DEFAULT_VIDEO_IMAGE_PATH);}

        if(videoDTO.episodeNumber != null){video.setEpisodeNumber(videoDTO.episodeNumber);}
        else{video.setEpisodeNumber(null);}

        if (videoDTO.previousVideoId != null) {
            video.setPreviousVideo(
                videoRepository.findById(videoDTO.previousVideoId)
                         .orElse(null)
            );
        }

        if (videoDTO.nextVideoId != null) {
            video.setNextVideo(getVideoById(videoDTO.nextVideoId));
        }

        if (videoDTO.seasonId != null) {
            video.setSeason(seasonDao.getSeasonById(videoDTO.seasonId));
        }

        if (videoDTO.tagIds != null) {
            List<Tags> tags = tagsDao.getTagsById(videoDTO.tagIds);
            video.setTagList(tags);
        }
        videoRepository.save(video);
    }
}
