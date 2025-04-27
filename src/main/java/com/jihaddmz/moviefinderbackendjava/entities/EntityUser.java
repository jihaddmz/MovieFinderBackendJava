package com.jihaddmz.moviefinderbackendjava.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    public EntityUser() {
    }

    public EntityUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
