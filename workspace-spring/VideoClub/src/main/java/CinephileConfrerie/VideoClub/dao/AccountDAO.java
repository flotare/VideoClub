package CinephileConfrerie.VideoClub.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Account.Role;
import CinephileConfrerie.VideoClub.model.Avis;
import jakarta.transaction.Transactional;

@Service
public class AccountDAO {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AvisDao avisDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Méthode pour changer les informations du compte ou en créer un nouveau
     * 
     * @param account
     * @return
     */
    public Account saveOrCreateAccount(Account account, Role role) {

        account.setMailAdress(account.getMailAdress());
        String hashed = passwordEncoder.encode(account.getPassword());
        account.setPassword(hashed);
        account.setPseudo(account.getPseudo());
        account.setRole(role);

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

    public Optional<Account> getByPseudo(String pseudo) {
        return accountRepository.findByPseudo(pseudo);
    }

    /**
     * Méthode pour log in
     * 
     * @param email
     * @param password
     * @return
     */
    public Optional<Account> login(String email, String password) {
        System.out.println("Tentative de connexion pour l'email : ");
        Optional<Account> acc = accountRepository.findByMailAdress(email);

        if (acc.isPresent() && passwordEncoder.matches(password, acc.get().getPassword())) {
            return acc;
        }
        return Optional.empty();
    }

    @Transactional
    public void delete(Long id) {
        // Récupérer l'account
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        account.getListAccountComment().size();

        // Supprimer tous les avis liés à cet account
        if (account.getListAccountComment() != null) {

            for (Avis avis : account.getListAccountComment()) {
                avisDao.deleteById(avis.getIdAvis());
            }
        }

        // Maintenant supprimer l'account
        accountRepository.delete(account);
    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }
}
