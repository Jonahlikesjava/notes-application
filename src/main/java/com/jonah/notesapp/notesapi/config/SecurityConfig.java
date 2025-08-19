package com.jonah.notesapp.notesapi.config;

import com.jonah.notesapp.notesapi.model.RoleEntity;
import com.jonah.notesapp.notesapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {
                })           // enable HTTP Basic auth
                .formLogin(form -> form.disable()); // no login page (API style)

        return http.build();
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
