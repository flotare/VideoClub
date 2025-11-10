package CinephileConfrerie.VideoClub.model.Media;

import java.time.LocalDate;
import java.util.List;

import CinephileConfrerie.VideoClub.model.Tags;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idVideo;

    private String title;
    private String description;
    
    private LocalDate releaseDate;
    private String imagePath;

    private Video previousVideo;
    private Video nextVide;


    @ManyToMany
    @JoinTable(
        name = "film_tag",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tags> tagList;


    public Video(Long idVideo, String title, String description, LocalDate releaseDate, String imagePath,
            Video previousVideo, Video nextVide, List<Tags> tagList) {
        this.idVideo = idVideo;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
        this.previousVideo = previousVideo;
        this.nextVide = nextVide;
        this.tagList = tagList;
    }


    public Video() {
    }

}
