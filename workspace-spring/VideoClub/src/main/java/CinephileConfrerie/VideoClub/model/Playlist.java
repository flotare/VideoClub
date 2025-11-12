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

    public Playlist(Long idPlaylist, Boolean privatePlaylist, List<Video> listVideoPlaylist,
            List<Serie> listSeriePlaylist, Account playlistAccount) {
        this.idPlaylist = idPlaylist;
        this.privatePlaylist = privatePlaylist;
        this.listVideoPlaylist = listVideoPlaylist;
        this.listSeriePlaylist = listSeriePlaylist;
        this.playlistAccount = playlistAccount;
    }

    public Playlist() {
    }

    /* GETTERS ET SETTERS */

    public Long getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(Long idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public Boolean getPrivatePlaylist() {
        return privatePlaylist;
    }

    public void setPrivatePlaylist(Boolean privatePlaylist) {
        this.privatePlaylist = privatePlaylist;
    }

    public List<Video> getListVideoPlaylist() {
        return listVideoPlaylist;
    }

    public void setListVideoPlaylist(List<Video> listVideoPlaylist) {
        this.listVideoPlaylist = listVideoPlaylist;
    }

    public List<Serie> getListSeriePlaylist() {
        return listSeriePlaylist;
    }

    public void setListSeriePlaylist(List<Serie> listSeriePlaylist) {
        this.listSeriePlaylist = listSeriePlaylist;
    }

    public Account getPlaylistAccount() {
        return playlistAccount;
    }

    public void setPlaylistAccount(Account playlistAccount) {
        this.playlistAccount = playlistAccount;
    }



    
}
