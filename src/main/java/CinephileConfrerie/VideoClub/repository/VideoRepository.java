package CinephileConfrerie.VideoClub.repository;

import CinephileConfrerie.VideoClub.model.Media.Video; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}