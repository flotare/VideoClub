package CinephileConfrerie.VideoClub.model;

import java.util.List;

import jakarta.persistence.Column;
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

    @Column(nullable=false)
    private String pseudo;

    @Column(nullable=false)
    private String mailAdress;

    @Column(nullable=false)
    private String password;

    @Column(nullable=true)
    private String accountDescription;

    @Column(nullable=true)
    private String profilePicturePath;

    @Column(nullable=false)
    @OneToMany(mappedBy = "playlistAccount")
    private List<Playlist> listAccountPlaylist;

    @Column(nullable=false)
    @OneToMany(mappedBy =  "avisAccount")
    private List<Avis> listAccountComment;

    public Account(Long idAccount, String pseudo, String mailAdress, String password, String accountDescription,
            String profilePicturePath, List<Playlist> listAccountPlaylist, List<Avis> listAccountComment) {
        this.idAccount = idAccount;
        this.pseudo = pseudo;
        this.mailAdress = mailAdress;
        this.password = password;
        this.accountDescription = accountDescription;
        this.profilePicturePath = profilePicturePath;
        this.listAccountPlaylist = listAccountPlaylist;
        this.listAccountComment = listAccountComment;
    }

    public Account() {
    }


    /* GETTERS ET SETTERS */

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public List<Playlist> getListAccountPlaylist() {
        return listAccountPlaylist;
    }

    public void setListAccountPlaylist(List<Playlist> listAccountPlaylist) {
        this.listAccountPlaylist = listAccountPlaylist;
    }

    public List<Avis> getListAccountComment() {
        return listAccountComment;
    }

    public void setListAccountComment(List<Avis> listAccountComment) {
        this.listAccountComment = listAccountComment;
    }
}
