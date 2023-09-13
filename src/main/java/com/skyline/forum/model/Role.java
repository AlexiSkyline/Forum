package com.skyline.forum.model;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated( EnumType.STRING )
    @Column( length = 20 )
    private TypeRole name;
}