package CinephileConfrerie.VideoClub.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Account;

@Service
public class AccountDAO {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        System.out.println("Tentative de création pour l'email : " + account.getMailAdress());
        if (accountRepository.existsByMailAdress(account.getMailAdress())) {
            throw new RuntimeException("Un compte existe déjà avec cette adresse mail");
        }
        if (accountRepository.existsByPseudo(account.getPseudo())) {
            throw new RuntimeException("Un compte existe déjà avec ce pseudo");
        }
        return accountRepository.save(account);
    }

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getByMail(String mail) {
        return accountRepository.findByMailAdress(mail);
    }

    public Optional<Account> login(String email, String password) {
        System.out.println("Tentative de connexion pour l'email : ");
        Optional<Account> acc = accountRepository.findByMailAdress(email);

        if (acc.isPresent() && acc.get().getPassword().equals(password)) {
            return acc;
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }
}
