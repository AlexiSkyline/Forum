package com.skyline.forum.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column( unique = true )
    private String username;

    @Column( unique = true )
    private String email;
    private String password;
}
