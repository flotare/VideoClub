package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("GENRE")
public class TagGenre extends Tags{

    @Column(nullable=true)
    private String genreName;

    public TagGenre(String genreName) {
        this.genreName = genreName;
    }
    public TagGenre(){}

    /* GETTERS ET SETTERS */

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
