package com.skyline.forum.controller;

import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/authentication")
public class AuthenticationController {
    private final IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        if (this.userService.userNameExists(signupRequestDto.getUsername())) return ResponseEntity.badRequest().build();
        if (this.userService.emailExists(signupRequestDto.getEmail())) return ResponseEntity.badRequest().build();

        this.userService.saveUser(signupRequestDto);
        return new ResponseEntity<>(this.userService.getUserByUsername(signupRequestDto.getUsername()), CREATED);
    }
}
