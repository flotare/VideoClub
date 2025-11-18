package CinephileConfrerie.VideoClub; 

import CinephileConfrerie.VideoClub.model.Tags;
import CinephileConfrerie.VideoClub.model.Media.Video;
import CinephileConfrerie.VideoClub.repository.VideoRepository;
import CinephileConfrerie.VideoClub.repository.TagsRepository; // Nous aurons besoin de ce Repository plus tard

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component // Indique à Spring de gérer et d'exécuter cette classe
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private VideoRepository videoRepository;

    // NOTE: Vous aurez besoin de créer un TagsRepository (voir Étape 2) !
    // @Autowired
    // private TagsRepository tagsRepository; 

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Démarrage de l'initialisation des données (Data Seeder) ---");
        
        long count = videoRepository.count();
        // 1. Création des Tags (Genres)
        // Tags action = new Tags(null, "Action"); // Si Tags n'est pas abstrait
        
        if (count == 0) {
        System.out.println("La table Video est vide. Insertion des données initiales...");

        // 1. Création des Vidéos (votre code existant)
        Video film1 = new Video(
            null, 
            "L'Odyssée de l'Espace",
            "Un film de science-fiction révolutionnaire.",
            LocalDate.of(1968, 4, 3),
            "/images/2001.jpg",
            null, 
            null, 
            null 
        );

        Video film2 = new Video(
            null,
            "Le Retour du Roi",
            "Le dernier volet de la trilogie du Seigneur des Anneaux.",
            LocalDate.of(2003, 12, 17),
            "/images/lotr.jpg",
            null,
            null,
            null 
        );

        // 2. Sauvegarde des Vidéos
        videoRepository.save(film1);
        videoRepository.save(film2);

        System.out.println("--- Deux vidéos ont été insérées dans la BDD. ---");
    } else {
        System.out.println("La table Video contient déjà " + count + " enregistrements. Ignorer l'insertion.");
        }
    }
}