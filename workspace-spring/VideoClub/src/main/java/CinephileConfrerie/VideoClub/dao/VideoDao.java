package CinephileConfrerie.VideoClub.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.dto.VideoDTO;
import CinephileConfrerie.VideoClub.model.Tags;
import CinephileConfrerie.VideoClub.model.Media.Serie;
import CinephileConfrerie.VideoClub.model.Media.Video;

@Service
public class VideoDao {

    private static final String DEFAULT_VIDEO_IMAGE_PATH = "./assets/default_video.png";
    private static final String DEFAULT_VIDEO_DESCRIPTION = "Aucune description disponible pour cette vidéo";

    @Autowired
    private VideoRepository videoRepository;

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

    public void deleteVideoById(Long id) {
        videoRepository.deleteById(id);
    }

    public void addVideo(VideoDTO videoDTO) {
        Video video;

        // Ajout attribut du nombre de saison si une série, sinon création de l'objet vidéo
        if (videoDTO.numberSeason != 0) {
            Serie serie = new Serie();
            serie.setNumberSeason(videoDTO.numberSeason);
            video = serie;
        } else {
            video = new Video();
        }

        video.setTitle(videoDTO.title);

        if (videoDTO.description != null) {
            video.setDescription(videoDTO.description);
        } else {
            video.setDescription(DEFAULT_VIDEO_DESCRIPTION);
        }

        if (videoDTO.releaseDate != null) {
            video.setReleaseDate(videoDTO.releaseDate);
        } else {
            video.setReleaseDate(null);
        }

        if (videoDTO.imagePath != null) {
            video.setImagePath(videoDTO.imagePath);
        } else {
            video.setImagePath(DEFAULT_VIDEO_IMAGE_PATH);
        }

        List<Tags> fullTags = new ArrayList<>();

        if (videoDTO.tagGenre != null) { //
            for (String name : videoDTO.tagGenre) {
                // Ajout des tags à la liste selon le genreName, ajoute également le genre à la
                // BDD s'il n'existe pas
                fullTags.add(tagsDao.getOrCreateGenre(name));
            }
        }

        if (videoDTO.tagActeur != null) {
            for (String actor : videoDTO.tagActeur) {
                // Même principe pour la liste des acteurs, prend en compte le fait que les
                // acteurs peuvent n'avoir qu'un pseudonyme
                fullTags.add(tagsDao.getOrCreateActor(actor));
            }
        }

        video.setTagList(fullTags);

        videoRepository.save(video);
    }
}
