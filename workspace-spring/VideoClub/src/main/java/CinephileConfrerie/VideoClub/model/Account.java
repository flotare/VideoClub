package CinephileConfrerie.VideoClub.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    private String pseudo;
    private String mailAdress;
    private String password;
    private String accountDescription;
    private String profilePicturePath;

    @OneToMany(mappedBy = "playlistAccount")
    private List<Playlist> listAccountPlaylist;

    @OneToMany(mappedBy =  "avisAccount")
    private List<Avis> listAccountComment;
    
}
