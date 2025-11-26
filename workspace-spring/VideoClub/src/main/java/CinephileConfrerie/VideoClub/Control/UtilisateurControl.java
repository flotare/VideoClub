package CinephileConfrerie.VideoClub.Control;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.model.Account;

@RestController
public class UtilisateurControl {

    @Autowired
    AccountDAO accountDAO;

    @PostMapping("/register")
    public Account register(@RequestBody Account account) {
        System.err.println("Registering account for email: " + account.getMailAdress());
        return accountDAO.createAccount(account);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        Optional<Account> user = accountDAO.login(email, password);
        System.out.println("user present: " + user.isPresent());
        if (user.isPresent()) {
            return Map.of(
                "status", "success",
                "id", user.get().getIdAccount(),
                "pseudo", user.get().getPseudo()
            );
        }
        System.out.println("Échec de la connexion pour l'email : " + email);
        return Map.of(
            "status", "error",
            "message", "Identifiants incorrects"
        );
    }

    @GetMapping("/account/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        return accountDAO.getById(id);
    }

    @PutMapping("/account/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account updated) {
        updated.setIdAccount(id);
        return accountDAO.update(updated);
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable Long id) {
        accountDAO.delete(id);
    }

}
