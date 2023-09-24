package com.skyline.forum.dto.topic;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicCourseDto {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String category;
}