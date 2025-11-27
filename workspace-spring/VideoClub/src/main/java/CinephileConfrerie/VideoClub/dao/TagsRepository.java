package CinephileConfrerie.VideoClub.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import CinephileConfrerie.VideoClub.model.Tags;
import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    List<Tags> findByTypeTag(String typeTag);
}

