package CinephileConfrerie.VideoClub.Control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.model.Account;

@RestController
public class UtilisateurControl {

    @Autowired
    AccountDAO accountDAO;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {

        try {
            Account saved = accountDAO.createAccount(account);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("id", saved.getIdAccount());
            response.put("pseudo", saved.getPseudo());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Erreur lors de la création du compte : " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
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
