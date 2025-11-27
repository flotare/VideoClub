package CinephileConfrerie.VideoClub.dto;

import java.time.LocalDate;
import java.util.List;

public class VideoDTO {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String imagePath;
    private List<String> tagGenre;
    private List<String> tagActeur;
    private int numberSeason;
    
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
    public List<String> getTagGenre() {
        return tagGenre;
    }
    public void setTagGenre(List<String> tagGenre) {
        this.tagGenre = tagGenre;
    }
    public List<String> getTagActeur() {
        return tagActeur;
    }
    public void setTagActeur(List<String> tagActeur) {
        this.tagActeur = tagActeur;
    }
    public int getNumberSeason() {
        return numberSeason;
    }
    public void setNumberSeason(int numberSeason) {
        this.numberSeason = numberSeason;
    }


    
}