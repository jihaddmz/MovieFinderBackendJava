package com.jihaddmz.moviefinderbackendjava.services;

import com.jihaddmz.moviefinderbackendjava.components.CustomException;
import com.jihaddmz.moviefinderbackendjava.entities.EntityUser;
import com.jihaddmz.moviefinderbackendjava.repositories.RepoUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceUser {

    private final RepoUser repoUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ServiceUser(RepoUser repoUser, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repoUser = repoUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public List<EntityUser> getAllUsers() {
        return repoUser.findAll();
    }

    public Map<String, String> signUpUser(String name, String email, String password) {
        try {
            repoUser.save(new EntityUser(name, email, passwordEncoder.encode(password)));
            Map<String, String> map = new HashMap<>();
            map.put("message", "Successfully created " + name);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> signInUser(String email, String password) {
        Optional<EntityUser> user = repoUser.findByEmail(email);
        if (user.isEmpty()) {
            throw new CustomException("User with email " + email + " not found", 404);
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new CustomException("Wrong password!", 400);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtService.generateToken(user.get().getEmail()));
        map.put("userId", user.get().getId());
        map.put("name", user.get().getName());

        return map;
    }
}
