package CinephileConfrerie.VideoClub.model;

import java.util.List;

import CinephileConfrerie.VideoClub.model.Media.Serie;
import CinephileConfrerie.VideoClub.model.Media.Video;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPlaylist;

    private Boolean privatePlaylist;

    @ManyToMany
    @JoinTable(
        name = "playlist_video",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> listVideoPlaylist;

    @ManyToMany
    @JoinTable(
        name = "playlist_serie",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "serie_id")
    )
    private List<Serie> listSeriePlaylist;

    @ManyToOne
    @JoinColumn(name="id_account")
    private Account playlistAccount;
}
