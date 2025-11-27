package CinephileConfrerie.VideoClub.dto;

public class SeasonDTO {
    private Long seasonNumber;
    private Long videoNumber;
    private Long serieId;
    
    public Long getSeasonNumber() {
        return seasonNumber;
    }
    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    public Long getVideoNumber() {
        return videoNumber;
    }
    public void setVideoNumber(Long videoNumber) {
        this.videoNumber = videoNumber;
    }
    public Long getSerieId() {
        return serieId;
    }
    public void setSerieId(Long serieId) {
        this.serieId = serieId;
    }

    
}
