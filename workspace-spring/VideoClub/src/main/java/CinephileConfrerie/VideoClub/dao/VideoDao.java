package CinephileConfrerie.VideoClub.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.dto.VideoDTO;
import CinephileConfrerie.VideoClub.model.TagActeur;
import CinephileConfrerie.VideoClub.model.TagGenre;
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

    public Video saveOrUpdate(VideoDTO videoDTO, Long id) {
        Video updatedVideo = getVideoById(id);

        if (videoDTO.getDescription() == null){updatedVideo.setDescription(DEFAULT_VIDEO_DESCRIPTION);}
        else {updatedVideo.setDescription(videoDTO.getDescription());}

        if (videoDTO.getImagePath() == null){updatedVideo.setImagePath(DEFAULT_VIDEO_IMAGE_PATH);}
        else {updatedVideo.setImagePath(videoDTO.getImagePath());}
        
        updatedVideo.setReleaseDate(videoDTO.getReleaseDate());
        updatedVideo.setTitle(videoDTO.getTitle());

        List<Tags> newTagList = new ArrayList<>();

        for(String tagString : videoDTO.getTagActeur()){
            TagActeur tag = (TagActeur)tagsDao.getOrCreateActor(tagString);
            newTagList.add(tag);
        }
        for(String tagString : videoDTO.getTagGenre()){
            TagGenre tag = (TagGenre)tagsDao.getOrCreateGenre(tagString);
            newTagList.add(tag);
        }
        updatedVideo.setTagList(newTagList);

        return videoRepository.save(updatedVideo);
    }

    public void deleteVideoById(Long id) {
        videoRepository.deleteById(id);
    }

    public Video addVideo(VideoDTO videoDTO) {
        Video video;

        // Ajout attribut du nombre de saison si une série, sinon création de l'objet vidéo
        if (videoDTO.getNumberSeason() != 0) {
            Serie serie = new Serie();
            serie.setNumberSeason(videoDTO.getNumberSeason());
            video = serie;
        } else {
            video = new Video();
        }

        // Les vidéos doivent avoir au moins un titre
        if (videoDTO.getTitle() == null){ 
            return null;
        }
        video.setTitle(videoDTO.getTitle());

        if (videoDTO.getDescription() != null) {
            video.setDescription(videoDTO.getDescription());
        } else {
            video.setDescription(DEFAULT_VIDEO_DESCRIPTION);
        }

        if (videoDTO.getReleaseDate() != null) {
            video.setReleaseDate(videoDTO.getReleaseDate());
        } else {
            video.setReleaseDate(null);
        }

        if (videoDTO.getImagePath() != null) {
            video.setImagePath(videoDTO.getImagePath());
        } else {
            video.setImagePath(DEFAULT_VIDEO_IMAGE_PATH);
        }

        List<Tags> fullTags = new ArrayList<>();

        if (videoDTO.getTagGenre() != null) { //
            for (String name : videoDTO.getTagGenre()) {
                // Ajout des tags à la liste selon le genreName, ajoute également le genre à la
                // BDD s'il n'existe pas
                fullTags.add(tagsDao.getOrCreateGenre(name));
            }
        }

        if (videoDTO.getTagActeur() != null) {
            for (String actor : videoDTO.getTagActeur()) {
                // Même principe pour la liste des acteurs, prend en compte le fait que les
                // acteurs peuvent n'avoir qu'un pseudonyme
                fullTags.add(tagsDao.getOrCreateActor(actor));
            }
        }

        video.setTagList(fullTags);

        return videoRepository.save(video);
    }
}
