package CinephileConfrerie.VideoClub.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

// SPRING SECURITY
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpServletResponse;

// Pour le PasswordEncoder 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                }) // <-- obligatoire si frontend séparé
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/avis/**", "/video/**", "/videos/**", "/recherche", "/register/**",
                                "/login/**", "/")
                        .permitAll()
                        .requestMatchers("/admin/**", "/api/**")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())

                // LOGIN JSON (pas de formulaire)
                .formLogin(form -> form.disable())

                .exceptionHandling(ex -> ex
                        // Quand un utilisateur non connecté tente d'accéder à une route protégée
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }))

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .permitAll())

                // Auth Manager = JSON login handler
                .userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}