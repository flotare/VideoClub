package CinephileConfrerie.VideoClub.model.Media;

import java.util.List;

/*
 * à voir si la classe va vraiment nous être utile
 */
public class Bilbiotheque {
    private List<Video> listeVideo;

    public Bilbiotheque(List<Video> listeVideo) {
        this.listeVideo = listeVideo;
    }

    public List<Video> getListeVideo() {
        return listeVideo;
    }

    public void setListeVideo(List<Video> listeVideo) {
        this.listeVideo = listeVideo;
    }

    
}
