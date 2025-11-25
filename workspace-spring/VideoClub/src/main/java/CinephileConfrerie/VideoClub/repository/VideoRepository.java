package CinephileConfrerie.VideoClub.repository;

import CinephileConfrerie.VideoClub.model.Media.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    /**
     * Recherche les vidéos dont le titre contient la chaîne de caractères fournie.
     * La recherche est insensible à la casse (IgnoreCase).
     * * @param title La chaîne de caractères à rechercher dans le titre.
     * @return Une liste de vidéos correspondantes.
     */
    List<Video> findByTitleContainingIgnoreCase(String title);
}