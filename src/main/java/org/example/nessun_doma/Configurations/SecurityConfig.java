package org.example.nessun_doma.Configurations;
import org.example.nessun_doma.Models.Ruolo;
import org.example.nessun_doma.Security.JwtAuthFilter;
import org.example.nessun_doma.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        Set permissions on endpoints
                .authorizeHttpRequests(auth -> auth
//            our public endpoints
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/corsi/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/prenotazioni/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/utenti/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/prenotazioni/**").hasAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/prenotazioni/**").hasRole(Ruolo.CLIENTE.name())
                        .requestMatchers(HttpMethod.DELETE, "/prenotazioni/**").hasAnyAuthority("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/corsi/**").hasRole(Ruolo.ISTRUTTORE.name())
                        .requestMatchers(HttpMethod.PUT, "/corsi/**").hasRole(Ruolo.ISTRUTTORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/corsi/**").hasRole(Ruolo.ISTRUTTORE.name())


//            our private endpoints
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}


