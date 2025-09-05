package com.jonah.notesapp.notesapi.config;

import com.jonah.notesapp.notesapi.model.RoleEntity;
import com.jonah.notesapp.notesapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Allow JSON auth calls without CSRF (keeps browsers from 403 on POST)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/auth/**"))

                .authorizeHttpRequests(auth -> auth
                        // PUBLIC static pages & assets
                        .requestMatchers(
                                "/", "/index.html",
                                "/login.html", "/register.html",
                                "/dashboard.html",
                                "/css/**", "/js/**", "/images/**", "/favicon.ico"
                        ).permitAll()

                        // PUBLIC auth APIs
                        .requestMatchers(HttpMethod.POST, "/api/auth/register", "/api/auth/login").permitAll()

                        // PROTECT the APIs (so dashboard JS must log in first)
                        .requestMatchers("/api/**").authenticated()

                        .anyRequest().permitAll() // or .authenticated() depending on your design
                )


                // we’re not using Spring’s form login or HTTP Basic
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }



    // Loads user details from DB for Spring Security during username/password auth
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return login -> repo.findByUsernameIgnoreCaseOrEmailIgnoreCase(login, login)
                .map(u -> User.withUsername(u.getUsername())
                        .password(u.getPassword()) // already BCrypt encoded
                        .roles(u.getRoles().stream().map(RoleEntity::getName).toArray(String[]::new))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));
    }


}
