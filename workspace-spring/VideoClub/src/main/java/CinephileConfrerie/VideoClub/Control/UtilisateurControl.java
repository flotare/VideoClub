package CinephileConfrerie.VideoClub.Control;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Account.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UtilisateurControl {

    @Autowired
    AccountDAO accountDAO;

    @PostMapping("/register/normal")
    public Map<String, Object> registerNormalUser(@RequestBody Account account, HttpServletRequest request) {
        try {
            if (account == null) {
                return Map.of(
                        "status", "error",
                        "message", "Données invalides");
            }
            Account newUser = accountDAO.saveOrCreateAccount(account, Role.ROLE_USER);

            this.login(account.getMailAdress(), account.getPassword(),request);

            return Map.of(
                    "status", "success",
                    "id", newUser.getIdAccount(),
                    "pseudo", newUser.getPseudo(),
                    "email", newUser.getMailAdress(),
                    "role", newUser.getRole());
        } catch (RuntimeException e) {
            return Map.of(
                    "status", "error",
                    "message",
                    "Un compte avec la même adresse e-mail ou le même pseudonyme existe déjà, veuillez réessayer en changeant ces informations.");
        }
    }

    @PostMapping("/register/admin")
    public Map<String, Object> registerAdminUser(@RequestBody Account account, HttpServletRequest request) {
        Account newUser = null;
        if (account != null) {
            newUser = accountDAO.saveOrCreateAccount(account, Role.ROLE_ADMIN);
        }
        
        this.login(account.getMailAdress(), account.getPassword(),request);

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
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String email = body.get("email");
        String password = body.get("password");

        try {
            Account account = accountDAO.login(email,password)
                    .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

            // Créer la session Spring Security
            this.login(email,password,request);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "id", account.getIdAccount(),
                    "pseudo", account.getPseudo(),
                    "email", account.getMailAdress(),
                    "role", account.getRole()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of(
                    "status", "error",
                    "message", "Email ou mot de passe incorrect"));
        }
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


    public void login(String email, String password, HttpServletRequest request){
        try {
            request.login(email, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
