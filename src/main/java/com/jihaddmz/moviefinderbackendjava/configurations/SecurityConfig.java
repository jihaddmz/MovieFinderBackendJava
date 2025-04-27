package com.jihaddmz.moviefinderbackendjava.configurations;

import com.jihaddmz.moviefinderbackendjava.components.JWTAuthenticationFilter;
import com.jihaddmz.moviefinderbackendjava.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter, MyUserDetailsService myUserDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(httpSecurity)) // don't block cors requests and use the one we define in CorsConfig class
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**", "/api/movies/**").permitAll()
                        .anyRequest().authenticated()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }
}
