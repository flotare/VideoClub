package CinephileConfrerie.VideoClub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TagGenre extends Tags{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idGenre;

    private String genreName;

    public TagGenre(Long idGenre, String genreName) {
        this.idGenre = idGenre;
        this.genreName = genreName;
    }

    /* GETTERS ET SETTERS */

    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
