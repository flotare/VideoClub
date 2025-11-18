package CinephileConfrerie.VideoClub.service;

import CinephileConfrerie.VideoClub.model.Media.Video;
import CinephileConfrerie.VideoClub.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indique que c'est une couche de logique métier
public class VideoService {

    @Autowired
    private VideoRepository videoRepository; // Injection du Repository

    // READ : Récupérer toutes les vidéos
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // READ : Récupérer une vidéo par ID
    public Optional<Video> getVideoById(Long id) {
        // La méthode findById renvoie un Optional (pour gérer les cas où l'ID n'existe pas)
        return videoRepository.findById(id);
    }

    // CREATE / UPDATE : Sauvegarder ou mettre à jour une vidéo
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    // DELETE : Supprimer une vidéo par ID
    public void deleteVideoById(Long id) {
        videoRepository.deleteById(id);
    }
}