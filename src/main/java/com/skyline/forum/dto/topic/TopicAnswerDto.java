package com.skyline.forum.dto.topic;

import com.skyline.forum.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopicAnswerDto {
    private Long id;
    private String message;
    private UserResponseDto author;
    private Boolean solution = false;
}
