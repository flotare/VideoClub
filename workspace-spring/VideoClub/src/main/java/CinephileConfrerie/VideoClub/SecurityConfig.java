package CinephileConfrerie.VideoClub;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

// SPRING SECURITY
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Pour le PasswordEncoder 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("recherche", "/register", "/login", "/video/**", "/").permitAll()
                .requestMatchers("/admin/**", "/api/**").hasRole("ADMIN") 
                .anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}