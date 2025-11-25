package CinephileConfrerie.VideoClub.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import CinephileConfrerie.VideoClub.model.Account;

@Service
public class AccountDAO {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (accountRepository.existsByMailAdress(account.getMailAdress())) {
            throw new RuntimeException("Un compte existe déjà avec cette adresse mail");
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

        if(acc.isPresent()) {
            System.out.println("Mail trouvé : " + acc.get().getMailAdress());
            System.out.println("Mot de passe DB : " + acc.get().getPassword());
            System.out.println("Mot de passe envoyé : " + password);
        }

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
