package CinephileConfrerie.VideoClub.dto;

import java.time.LocalDate;
import java.util.List;

public class VideoDTO {
    public String title;
    public String description;
    public LocalDate releaseDate;
    public String imagePath;
    public Integer episodeNumber;
    public Long previousVideoId;
    public Long nextVideoId;
    public Long seasonId;
    public List<Long> tagIds;
}