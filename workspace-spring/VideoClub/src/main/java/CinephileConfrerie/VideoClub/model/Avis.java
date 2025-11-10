package CinephileConfrerie.VideoClub.model;

import CinephileConfrerie.VideoClub.model.Media.Serie;
import CinephileConfrerie.VideoClub.model.Media.Video;
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

    private Integer note;
    private String comment;

    @ManyToOne
    @JoinColumn(name="idAvis_Account")
    private Account avisAccount;

    @ManyToOne
    @JoinColumn(name="idAvis_Video", nullable = true)
    private Video avisVideo;

    @ManyToOne
    @JoinColumn(name="idAvis_Serie", nullable = true)
    private Serie avisSerie;

}
