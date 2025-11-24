package CinephileConfrerie.VideoClub.model.Media;

import java.time.LocalDate;
import java.util.List;

import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Playlist;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idSerie;

    @Column(nullable=false)
    private String title;

    @Lob  // Crée un large object
    @Column(columnDefinition = "TEXT", nullable=true)
    private String description;

    @Column(nullable=true)
    private LocalDate releaseDate;

    @Column(nullable=true)
    private String imagePath;
    
    @Column(nullable=false)
    private Long numberOfSeason;


    @OneToMany(mappedBy = "serie")
    private List<Season> listeSeason; // Liste des saisons de la série

    @ManyToMany(mappedBy = "listSeriePlaylist")
    private List<Playlist> listPlaylist; // Liste des playlists dans lesquelles la série apparaît

    @OneToMany(mappedBy = "avisSerie")
    private List<Avis> listAvis; // Liste des avis sur la série

    public Serie(Long idSerie, Long numberOfSeason, List<Season> listeSeason, List<Playlist> listPlaylist,
            List<Avis> listAvis) {
        this.idSerie = idSerie;
        this.numberOfSeason = numberOfSeason;
        this.listeSeason = listeSeason;
        this.listPlaylist = listPlaylist;
        this.listAvis = listAvis;
    }

    public Serie() {
    }

    /* GETTERS ET SETTERS */

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public Long getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(Long numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public List<Season> getListeSeason() {
        return listeSeason;
    }

    public void setListeSeason(List<Season> listeSeason) {
        this.listeSeason = listeSeason;
    }

    public List<Playlist> getListPlaylist() {
        return listPlaylist;
    }

    public void setListPlaylist(List<Playlist> listPlaylist) {
        this.listPlaylist = listPlaylist;
    }

    public List<Avis> getListAvis() {
        return listAvis;
    }

    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
    }
}
