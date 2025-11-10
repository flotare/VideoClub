package CinephileConfrerie.VideoClub.model.Media;

import java.util.List;

import CinephileConfrerie.VideoClub.model.Avis;
import CinephileConfrerie.VideoClub.model.Playlist;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idSerie;

    @OneToMany(mappedBy = "serie")
    private List<Season> listeSeason; // Liste des saisons de la série

    @ManyToMany(mappedBy = "listSeriePlaylist")
    private List<Playlist> listPlaylist; // Liste des playlists dans lesquelles la série apparaît

    @OneToMany(mappedBy = "avisSerie")
    private List<Avis> listAvis; // Liste des avis sur la série
}
