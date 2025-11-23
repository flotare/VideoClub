package CinephileConfrerie.VideoClub.model.Media;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idSaison;

    @Column(nullable=false)
    private Long seasonNumber;

    @Column(nullable=false)
    private Long videoNumber;

    @OneToMany(mappedBy = "season")
    private List<Video> listeVideoSeason;

    @ManyToOne
    @JoinColumn(name = "idSeason_Serie")
    private Serie serie;

    public Season(Long idSaison, Long seasonNumber, Long videoNumber, List<Video> listeVideoSeason, Serie serie) {
        this.idSaison = idSaison;
        this.seasonNumber = seasonNumber;
        this.videoNumber = videoNumber;
        this.listeVideoSeason = listeVideoSeason;
        this.serie = serie;
    }

    public Season() {
    }

    /* GETTERS ET SETTERS */

    public Long getIdSaison() {
        return idSaison;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Long getVideoNumber() {
        return videoNumber;
    }

    public void setVideoNumber(Long videoNumber) {
        this.videoNumber = videoNumber;
    }

    public List<Video> getListeVideoSeason() {
        return listeVideoSeason;
    }

    public void setListeVideoSeason(List<Video> listeVideoSeason) {
        this.listeVideoSeason = listeVideoSeason;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setIdSaison(Long idSaison) {
        this.idSaison = idSaison;
    }

    
}
