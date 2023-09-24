package com.skyline.forum.dto.topic;

import com.skyline.forum.model.enums.StatusTopic;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String message;
    @NotNull
    private StatusTopic status;
    @Valid @NotNull
    private TopicCourseDto course;
}
