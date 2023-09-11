package com.skyline.forum.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
public class CourseRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String category;
}
