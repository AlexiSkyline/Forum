package com.skyline.forum.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
}
