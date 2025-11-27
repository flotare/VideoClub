package CinephileConfrerie.VideoClub.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CinephileConfrerie.VideoClub.model.Avis;


public interface AvisRepository extends JpaRepository<Avis,Long>{
    List<Avis> findByAvisVideo_IdVideo(Long idVideo);
    List<Avis> findByAvisAccount_IdAccount(Long idAccount);
}
