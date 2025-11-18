package CinephileConfrerie.VideoClub.repository;

import CinephileConfrerie.VideoClub.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {
    // Aucune méthode à ajouter
}