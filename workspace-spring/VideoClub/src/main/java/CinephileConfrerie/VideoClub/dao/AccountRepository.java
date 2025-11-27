package CinephileConfrerie.VideoClub.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import CinephileConfrerie.VideoClub.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{

    Optional<Account> findByMailAdress(String mailAdress);

    Optional<Account> findByPseudo(String pseudo);

    boolean existsByMailAdress(String mailAdress);

    boolean existsByPseudo(String pseudo);

}
