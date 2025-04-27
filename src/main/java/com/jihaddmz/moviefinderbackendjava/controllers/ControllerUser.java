package com.jihaddmz.moviefinderbackendjava.controllers;

import com.jihaddmz.moviefinderbackendjava.dtos.DtoUser;
import com.jihaddmz.moviefinderbackendjava.services.ServiceUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    private final ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @PostMapping("/signup")
    public Map<String, String> signUp(@RequestBody DtoUser user) {
        return serviceUser.signUpUser(user.getName(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/signin")
    public Map<String, Object> signIn(@RequestBody Map<String, String> map) {
        return serviceUser.signInUser(map.get("email"), map.get("password"));
    }
}
