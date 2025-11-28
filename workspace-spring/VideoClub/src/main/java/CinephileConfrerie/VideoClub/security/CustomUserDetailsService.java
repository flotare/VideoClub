package CinephileConfrerie.VideoClub.security;

import CinephileConfrerie.VideoClub.model.Account;
import CinephileConfrerie.VideoClub.dao.AccountRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByMailAdress(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getMailAdress())
                .password(account.getPassword())
                .roles(account.getRole().name().replace("ROLE_", "")) // Spring attend juste "ADMIN", pas "ROLE_ADMIN"
                .build();
    }
}