package CinephileConfrerie.VideoClub.model.Media;

import java.time.LocalDate;
import java.util.List;

import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Playlist;
import CinephileConfrerie.VideoClub.model.Tags;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("SERIE")
public class Serie extends Video {

    @Column(nullable = false)
    private Long numberSeason;

    @OneToMany(mappedBy = "serie")
    private List<Season> listeSeason; // Liste des saisons de la série

    public Serie(Long idVideo, String title, String description, LocalDate releaseDate, String imagePath,
            List<Playlist> listPlaylist, List<Avis> listAvis, List<Tags> tagList, Long numberSeason,
            List<Season> listeSeason) {
        super(idVideo, title, description, releaseDate, imagePath, listPlaylist, listAvis, tagList);
        this.numberSeason = numberSeason;
        this.listeSeason = listeSeason;
    }

    public Serie(Long numberSeason, List<Season> listeSeason) {
        this.numberSeason = numberSeason;
        this.listeSeason = listeSeason;
    }

    public Serie() {
    }

    /* GETTERS ET SETTERS */

    public Long getNumberSeason() {
        return numberSeason;
    }

    public void setNumberOfSeason(Long numberSeason) {
        this.numberSeason = numberSeason;
    }

    public List<Season> getListeSeason() {
        return listeSeason;
    }

    public void setListeSeason(List<Season> listeSeason) {
        this.listeSeason = listeSeason;
    }
}
