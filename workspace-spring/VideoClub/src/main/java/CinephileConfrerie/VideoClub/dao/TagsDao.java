package CinephileConfrerie.VideoClub.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.TagActeur;
import CinephileConfrerie.VideoClub.model.TagGenre;
import CinephileConfrerie.VideoClub.model.Tags;
import CinephileConfrerie.VideoClub.model.Media.Video;

@Service
public class TagsDao {

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private VideoRepository videoRepository;

    /**
     * Récupérer tous les tags
     * @return
     */
    public List<Tags> findAll() {
        return tagsRepository.findAll();
    }

    public Tags getTagsById(Long id) {
        return tagsRepository.findById(id).orElse(null);
    }

    /**
     * Récupérer une liste de tags précises selon une liste d'id
     * @param ids
     * @return
     */
    public List<Tags> getTagsById(List<Long> ids) {
        return tagsRepository.findAllById(ids);
    }


    public List<Tags> getAllActors() {
        return tagsRepository.findAllActors();
    }

    public List<Tags> getAllGenres() {
        return tagsRepository.findAllGenres();
    }

    /**
     * Fonction qui fournit le genre trouvé dans la BDD selon son attribut genreName
     * ou bien le crée.
     * à utiliser judicieusement pour ne pas créer tout et n'importe quoi
     * 
     * @param name
     * @return
     */
    public Tags getOrCreateGenre(String name) {
        return tagsRepository.findByGenreName(name)
                .orElseGet(() -> {
                    TagGenre genre = new TagGenre();
                    genre.setGenreName(name);
                    return tagsRepository.save(genre);
                });
    }

    /**
     * Même fontion que getOrCreateGenre pour les acteurs, logique plus lourde pour
     * gérer les pseudonymes d'acteurs
     * (sans nom de famille)
     * 
     * @param fullName
     * @return
     */
    public Tags getOrCreateActor(String fullName) {

        String[] parts = fullName.split(" ");

        String firstName = parts[0];
        // Concatène le reste des String pour le nom de famille (par ex: Jean-Claude Van
        // Damme)
        String lastName = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : null;

        return tagsRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseGet(() -> {
                    TagActeur actor = new TagActeur();
                    actor.setFirstName(firstName);
                    actor.setLastName(lastName);
                    return tagsRepository.save(actor);
                });
    }

    public void deleteTagsById(Long id) {
        // Il est nécessaire de supprimer les tags de la liste des vidéos qui ont ce
        // tags avant de le supprimer de sa table
        Optional<Tags> tag = tagsRepository.findById(id);
        List<Video> videoWithTag = videoRepository.findByTagList_IdTag(id);
        for (Video video : videoWithTag) {
            video.getTagList().remove(tag);
        }
        tagsRepository.deleteById(id);
    }

    public void saveActor(TagActeur acteur, Long id) {
        Optional<Tags> updatedActor = tagsRepository.findById(id);

        ((TagActeur) updatedActor.get()).setFirstName(acteur.getFirstName());
        ((TagActeur) updatedActor.get()).setLastName(acteur.getLastName());

        tagsRepository.save(updatedActor.get());
    }

    public void saveGenre(TagGenre genre, Long id) {
        Optional<Tags> updatedGenre = tagsRepository.findById(id);

        ((TagGenre) updatedGenre.get()).setGenreName(genre.getGenreName());

        tagsRepository.save(updatedGenre.get());
    }
}
