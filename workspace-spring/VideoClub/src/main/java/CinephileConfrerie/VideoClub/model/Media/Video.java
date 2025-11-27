package CinephileConfrerie.VideoClub.model.Media;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Playlist;
import CinephileConfrerie.VideoClub.model.Tags;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_video", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("FILM")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    @Column(nullable=false)
    private String title;

    @Lob  // Crée un large object
    @Column(columnDefinition = "TEXT", nullable=true)
    private String description;

    @Column(nullable=true)
    private LocalDate releaseDate;

    @Column(nullable=true)
    private String imagePath;

    @ManyToMany(mappedBy = "listVideoPlaylist")
    private List<Playlist> listPlaylist; // Liste des playlists dans lesquelles apparaît la vidéo

    
    @OneToMany(mappedBy = "avisVideo")
    @JsonBackReference
    private List<Avis> listAvis; // Liste des avis liés à la vidéo

    @ManyToMany
    @JoinTable(name = "video_tag", joinColumns = @JoinColumn(name = "id_video"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private List<Tags> tagList; // Liste des tags de la vidéo

    public Video(Long idVideo, String title, String description, LocalDate releaseDate, String imagePath,
            List<Playlist> listPlaylist, List<Avis> listAvis,List<Tags> tagList) {
        this.idVideo = idVideo;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
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