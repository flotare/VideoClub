package CinephileConfrerie.VideoClub;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import CinephileConfrerie.VideoClub.dao.AccountDAO;
import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.model.Account.Role;

@SpringBootTest
public class AccountDAOTest {

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testCreateCheckDeleteAccounts() {
        // Supprimer les comptes existants si présents
        accountDAO.getByPseudo("testuser").ifPresent(acc -> accountDAO.delete(acc.getIdAccount()));
        accountDAO.getByMail("testuser@example.com").ifPresent(acc -> accountDAO.delete(acc.getIdAccount()));

        accountDAO.getByPseudo("adminuser").ifPresent(acc -> accountDAO.delete(acc.getIdAccount()));
        accountDAO.getByMail("admin@example.com").ifPresent(acc -> accountDAO.delete(acc.getIdAccount()));

        // Création d'un compte normal
        Account user = new Account();
        user.setPseudo("testuser");
        user.setMailAdress("testuser@example.com");
        user.setPassword("password123");
        Account savedUser = accountDAO.saveOrCreateAccount(user, Role.ROLE_USER);

        // Création d'un compte admin
        Account admin = new Account();
        admin.setPseudo("adminuser");
        admin.setMailAdress("admin@example.com");
        admin.setPassword("adminpass");
        Account savedAdmin = accountDAO.saveOrCreateAccount(admin, Role.ROLE_ADMIN);

        // Vérification des rôles
        assertEquals(Role.ROLE_USER, savedUser.getRole(), "Le rôle de l'utilisateur doit être ROLE_USER");
        assertEquals(Role.ROLE_ADMIN, savedAdmin.getRole(), "Le rôle de l'admin doit être ROLE_ADMIN");

        // Vérification que les comptes existent dans la base
        Optional<Account> fetchedUser = accountDAO.getById(savedUser.getIdAccount());
        Optional<Account> fetchedAdmin = accountDAO.getById(savedAdmin.getIdAccount());
        assertTrue(fetchedUser.isPresent(), "L'utilisateur doit exister dans la base");
        assertTrue(fetchedAdmin.isPresent(), "L'admin doit exister dans la base");

        // Suppression des comptes
        accountDAO.delete(savedUser.getIdAccount());
        accountDAO.delete(savedAdmin.getIdAccount());

        // Vérification qu'ils ont été supprimés
        assertFalse(accountDAO.getById(savedUser.getIdAccount()).isPresent(), "L'utilisateur ne doit plus exister");
        assertFalse(accountDAO.getById(savedAdmin.getIdAccount()).isPresent(), "L'admin ne doit plus exister");
    }
}
