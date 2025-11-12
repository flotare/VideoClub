package CinephileConfrerie.VideoClub.model.Media;

import java.time.LocalDate;
import java.util.List;

import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Playlist;
import CinephileConfrerie.VideoClub.model.Tags;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idVideo;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String imagePath;

    @OneToOne
    @JoinColumn(name="idVideo_PreviousVideo",nullable = true)
    private Video previousVideo; // Vidéo précédente (si série)
    
    @OneToOne
    @JoinColumn(name="idVideo_nextVideo",nullable = true)
    private Video nextVideo; // Vidéo suivante (si série)

    @ManyToOne
    @JoinColumn(name = "idVideo_Season", nullable = true)
    private Season season;

    @ManyToMany(mappedBy = "listVideoPlaylist")
    private List<Playlist> listPlaylist; // Liste des playlists dans lesquelles apparaît la vidéo

    @OneToMany(mappedBy = "avisVideo")
    private List<Avis> listAvis; // Liste des avis liés à la vidéo

    @ManyToMany
    @JoinTable(
        name = "film_tag",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tags> tagList; // Liste des tags de la vidéo

    public Video(Long idVideo, String title, String description, LocalDate releaseDate, String imagePath,
            Video previousVideo, Video nextVideo, Season season, List<Playlist> listPlaylist, List<Avis> listAvis,
            List<Tags> tagList) {
        this.idVideo = idVideo;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
        this.previousVideo = previousVideo;
        this.nextVideo = nextVideo;
        this.season = season;
        this.listPlaylist = listPlaylist;
        this.listAvis = listAvis;
        this.tagList = tagList;
    }

    public Video() {
    }



    /* GETTERS ET SETTERS */

    public Long getIdVideo() {
        return idVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Video getPreviousVideo() {
        return previousVideo;
    }

    public void setPreviousVideo(Video previousVideo) {
        this.previousVideo = previousVideo;
    }

    public Video getNextVideo() {
        return nextVideo;
    }

    public void setNextVideo(Video nextVideo) {
        this.nextVideo = nextVideo;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public List<Tags> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tags> tagList) {
        this.tagList = tagList;
    }


    






}