package CinephileConfrerie.VideoClub.Control;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Account.Role;

@RestController
public class UtilisateurControl {

    @Autowired
    AccountDAO accountDAO;

    @PostMapping("/register/normal")
    public Map<String, Object> registerNormalUser(@RequestBody Account account) {
        Account newUser = null;
        if (account != null) {
            newUser = accountDAO.saveOrCreateAccount(account, Role.ROLE_USER);
        }

        return (newUser != null) ? Map.of(
                "status", "success",
                "id", newUser.getIdAccount(),
                "pseudo", newUser.getPseudo(),
                "email", newUser.getMailAdress(),
                "role", newUser.getRole())
                : Map.of(
                        "status", "error",
                        "message", "Erreur lors de la création du compte");
    }

    @PostMapping("/register/admin")
    public Map<String, Object> registerAdminUser(@RequestBody Account account) {
        Account newUser = null;
        if (account != null) {
            newUser = accountDAO.saveOrCreateAccount(account, Role.ROLE_ADMIN);
        }

        return (newUser != null) ? Map.of(
                "status", "success",
                "id", newUser.getIdAccount(),
                "pseudo", newUser.getPseudo(),
                "email", newUser.getMailAdress(),
                "role", newUser.getRole())
                : Map.of(
                        "status", "error",
                        "message", "Erreur lors de la création du compte");
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
                    "pseudo", user.get().getPseudo(),
                    "email", user.get().getMailAdress());
        }
        System.out.println("Échec de la connexion pour l'email : " + email);
        return Map.of(
                "status", "error",
                "message", "Identifiants incorrects");
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
