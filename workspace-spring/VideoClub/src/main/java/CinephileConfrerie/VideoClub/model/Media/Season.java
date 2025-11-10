package CinephileConfrerie.VideoClub.model.Media;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Season {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idSaison;

    private Long seasonNumber;
    private Long videoNumber;

    @OneToMany(mappedBy = "season")
    private List<Video> listeVideoSeason;

    @ManyToOne
    @JoinColumn(name = "idSeason_Serie")
    private Serie serie;
}
