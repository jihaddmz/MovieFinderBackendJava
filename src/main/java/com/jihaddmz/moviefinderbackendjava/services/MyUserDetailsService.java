package com.jihaddmz.moviefinderbackendjava.services;

import com.jihaddmz.moviefinderbackendjava.entities.EntityUser;
import com.jihaddmz.moviefinderbackendjava.repositories.RepoUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final RepoUser repoUser;

    public MyUserDetailsService(RepoUser repoUser) {
        this.repoUser = repoUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EntityUser user = repoUser.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));

        return User.withUsername(user.getEmail()).password(user.getPassword()).authorities("USER").build();
    }
}
