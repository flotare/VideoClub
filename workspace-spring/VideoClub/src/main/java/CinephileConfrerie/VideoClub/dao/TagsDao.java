package CinephileConfrerie.VideoClub.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.TagActeur;
import CinephileConfrerie.VideoClub.model.TagGenre;
import CinephileConfrerie.VideoClub.model.Tags;

@Service
public class TagsDao {

    @Autowired
    private TagsRepository tagsRepository;

    public List<Tags> findAll(){return tagsRepository.findAll();}

    public Tags getTagsById(Long id){
        return tagsRepository.findById(id).orElse(null);
    }

    /* 
    public List<Tags> getTagByType(String type){
        return tagsRepository.findByTypeTag(type);
    }*/

    public List<Tags> getTagsById(List<Long> ids){
        return tagsRepository.findAllById(ids);
    }

    public List<Tags> getAllActors(){
        return tagsRepository.findAllActors();
    }

    public List<Tags> getAllGenres(){
        return tagsRepository.findAllGenres();
    }

    /**
     * Fonction qui fournit le genre trouvé dans la BDD selon son attribut genreName ou bien le crée.
     * à utiliser judicieusement pour ne pas créer tout et n'importe quoi
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
     * Même contion que getOrCreateGenre pour les acteurs, logique plus lourde pour gérer les pseudonymes d'acteurs
     * (sans nom de famille)
     * @param fullName
     * @return
     */
    public Tags getOrCreateActor(String fullName) {

        String[] parts = fullName.split(" ");

        String firstName = parts[0];
        // Concatène le reste des String pour le nom de famille (par ex: Jean-Claude Van Damme)
        String lastName = parts.length > 1 ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)): null;
        
        return tagsRepository.findByFirstNameAndLastName(firstName, lastName)
            .orElseGet(() -> {
                TagActeur actor = new TagActeur();
                actor.setFirstName(firstName);
                actor.setLastName(lastName);
                return tagsRepository.save(actor);
            });
    }

    public void deleteTagsById(Long id){
        tagsRepository.deleteById(id);
    }

}
