package com.skyline.forum.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter @Setter
public class AuthenticationResponseDto {
    private String token;
    private final String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
}