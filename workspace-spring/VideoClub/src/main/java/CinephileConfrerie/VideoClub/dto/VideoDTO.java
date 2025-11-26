package CinephileConfrerie.VideoClub.dto;

import java.time.LocalDate;
import java.util.List;

public class VideoDTO {
    public String title;
    public String description;
    public LocalDate releaseDate;
    public String imagePath;
    public List<String> tagGenre;
    public List<String> tagActeur;
    public int numberSeason;
}