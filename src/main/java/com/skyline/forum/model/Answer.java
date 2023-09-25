package com.skyline.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDateTime dateCreated = LocalDateTime.now();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    private Boolean solution = false;
}
