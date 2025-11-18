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
import jakarta.persistence.ManyToOne;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idVideo;
    
    // --- Champs de base ---
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String imagePath;

    // --- Relations d'Entité ---
    @ManyToOne 
    @JoinColumn(name = "previous_video_id") 
    private Video previousVideo;

    @ManyToOne 
    @JoinColumn(name = "next_video_id") 
    private Video nextVide;

    @ManyToMany
    @JoinTable(
        name = "film_tag",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tags> tags;

    // --- Constructeurs ---

    public Video(Long idVideo, String title, String description, LocalDate releaseDate, String imagePath,
                Video previousVideo, Video nextVide, List<Tags> tags) {
        this.idVideo = idVideo;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
        this.previousVideo = previousVideo;
        this.nextVide = nextVide;
        this.tags = tags;
    }

    public Video() {
    }

    // Getters/Setters pour idVideo 
    public Long getIdVideo() {
        return idVideo;
    }
    public void setIdVideo(Long idVideo) { 
        this.idVideo = idVideo;
    }

    // Getters/Setters pour title (String)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Getters/Setters pour description (String)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters/Setters pour releaseDate (LocalDate)
    public LocalDate getReleaseDate(){ 
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) { 
        this.releaseDate = releaseDate;
    }
    
    // Getters/Setters pour imagePath (String)
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    // Getters/Setters pour previousVideo (Video)
    public Video getPreviousVideo() {
        return previousVideo;
    }
    public void setPreviousVideo(Video previousVideo) {
        this.previousVideo = previousVideo;
    }

    // Getters/Setters pour nextVide (Video)
    //public Video getNextVide() {
    //    return nextVide;
    //}
    public void setNextVide(Video nextVide) {
        this.nextVide = nextVide;
    } 

    // Getters/Setters pour tags (List<Tags>)
    public List<Tags> getTags() {
        return tags;
    }
    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}