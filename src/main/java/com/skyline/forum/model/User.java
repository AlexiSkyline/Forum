package com.skyline.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable( name = "user_roles", joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Set<Role> roles = new HashSet<>();
}
