package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ACTEUR")
public class TagActeur extends Tags {

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    public TagActeur(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TagActeur() {
    }

    /* GETTERS ET SETTERS */

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
