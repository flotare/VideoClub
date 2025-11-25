package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TagActeur extends Tags{

 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idActeur;

    private String firstName;
    private String lastName;


    public TagActeur(Long idActeur, String firstName, String lastName) {
        this.idActeur = idActeur;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /* GETTERS ET SETTERS */

    public Long getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(Long idActeur) {
        this.idActeur = idActeur;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    
}
