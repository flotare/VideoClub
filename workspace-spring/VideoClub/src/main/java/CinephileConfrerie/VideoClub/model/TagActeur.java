package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TagActeur {

 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idActeur;

    private String firstName;
    private String lastName;

}
