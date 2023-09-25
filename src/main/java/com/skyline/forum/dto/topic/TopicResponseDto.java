package com.skyline.forum.dto.topic;

import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.model.enums.StatusTopic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
public class TopicResponseDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreated;
    private StatusTopic status;
    private UserResponseDto author;
    private CourseResponseDto course;
    private Set<TopicAnswerDto> answers;
}
