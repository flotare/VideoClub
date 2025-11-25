package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TagGenre {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idGenre;

    private String genreName;
}