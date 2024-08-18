package com.projet.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projet.app.filters.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final JWTAuthenticationFilter jwtAuthenticationFilter;

   
	@Autowired
    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
		
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/signup/**", "/login").permitAll()
                .requestMatchers("/api/etudiant/**").hasRole("ADMIN")
                .requestMatchers("/{id}/monsolde").hasRole("ETUDIANT")
                .requestMatchers("/api/menus/**").hasAnyRole("CHEF","ADMIN")
                .requestMatchers("/menuDujour").authenticated()
                .requestMatchers("/api/paiements").hasRole("ETUDIANT")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/reclamation/**").hasRole("ETUDIANT")
                .requestMatchers("/reclamations/**").hasRole("ADMIN")
                .requestMatchers("/api/restaurant").authenticated()
                .requestMatchers("/api/proposition/**").authenticated()
                .requestMatchers("/rechargerCarte").hasRole("ETUDIANT")
                .requestMatchers("/ajouterAvis").permitAll()
                .requestMatchers("/transferersolde").permitAll()
                .requestMatchers("/listerAvis").permitAll()
                .requestMatchers("/api/chef/**").hasRole("ADMIN")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
	@Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
}