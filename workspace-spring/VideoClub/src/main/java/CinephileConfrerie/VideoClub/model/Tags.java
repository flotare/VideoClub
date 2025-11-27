package CinephileConfrerie.VideoClub.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

import CinephileConfrerie.VideoClub.model.Media.Video;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_tag", discriminatorType = DiscriminatorType.STRING)
public abstract class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;

    @ManyToMany(mappedBy = "tagList")
    private List<Video> videos;

    @Transient
    private String typeTag;

    // getter et setter pour typeTag
    public String getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(String typeTag) {
        this.typeTag = typeTag;
    }
}
