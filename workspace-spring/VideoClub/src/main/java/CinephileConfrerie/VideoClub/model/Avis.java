package CinephileConfrerie.VideoClub.model;

import CinephileConfrerie.VideoClub.model.Media.Serie;
import CinephileConfrerie.VideoClub.model.Media.Video;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Avis {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAvis;

    @Column(nullable=false)
    private Integer note;
    
    @Column(nullable=true)
    private String comment;

    @ManyToOne
    @JoinColumn(name="idAvis_Account", nullable=false)
    private Account avisAccount;

    @ManyToOne
    @JoinColumn(name="idAvis_Video", nullable = true)
    private Video avisVideo;

    @ManyToOne
    @JoinColumn(name="idAvis_Serie", nullable = true)
    private Serie avisSerie;

    public Avis(Long idAvis, Integer note, String comment, Account avisAccount, Video avisVideo, Serie avisSerie) {
        this.idAvis = idAvis;
        this.note = note;
        this.comment = comment;
        this.avisAccount = avisAccount;
        this.avisVideo = avisVideo;
        this.avisSerie = avisSerie;
    }

    public Avis() {
    }

    /* GETTERS ET SETTERS */

    public Long getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(Long idAvis) {
        this.idAvis = idAvis;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getAvisAccount() {
        return avisAccount;
    }

    public void setAvisAccount(Account avisAccount) {
        this.avisAccount = avisAccount;
    }

    public Video getAvisVideo() {
        return avisVideo;
    }

    public void setAvisVideo(Video avisVideo) {
        this.avisVideo = avisVideo;
    }

    public Serie getAvisSerie() {
        return avisSerie;
    }

    public void setAvisSerie(Serie avisSerie) {
        this.avisSerie = avisSerie;
    }
}
