package com.jihaddmz.moviefinderbackendjava.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class ControllerPing {


    public ControllerPing() {

    }

    @RequestMapping(value = "", method = RequestMethod.HEAD)
    public ResponseEntity<Void> ping() {
        return ResponseEntity.ok().build();
    }
}
