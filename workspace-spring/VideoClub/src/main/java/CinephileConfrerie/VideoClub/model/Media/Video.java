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
}