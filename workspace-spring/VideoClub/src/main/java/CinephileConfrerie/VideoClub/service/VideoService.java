package CinephileConfrerie.VideoClub.service;

import CinephileConfrerie.VideoClub.model.Media.Video;
import CinephileConfrerie.VideoClub.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    // READ : Récupérer toutes les vidéos
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // READ : Récupérer une vidéo par ID
    public Optional<Video> getVideoById(Long id) {
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

    /**
     * Recherche les vidéos par titre ou retourne toute la liste si le titre est vide.
     * * @param title Le terme de recherche.
     * @return Une liste de vidéos filtrée ou complète.
     */
    public List<Video> searchVideosByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            // Si le terme de recherche est vide ou nul, on retourne toute la liste.
            return videoRepository.findAll();
        }
        // Sinon, on utilise la méthode de recherche spécifique.
        return videoRepository.findByTitleContainingIgnoreCase(title);
    }
}