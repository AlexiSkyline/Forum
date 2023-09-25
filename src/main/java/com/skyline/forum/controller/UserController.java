package com.skyline.forum.controller;

import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.dto.user.UserUpdateDto;
import com.skyline.forum.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/users")
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        return new ResponseEntity<>(this.userService.getUsers(), OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
        UserResponseDto userResponseDto = this.userService.getUserByUsername(username);
        if (userResponseDto == null) return ResponseEntity.notFound().build();

        return new ResponseEntity<>(userResponseDto, OK);
    }

    @PutMapping("{username}")
    @SecurityRequirement(name = "JWT Bearer Token")
    public ResponseEntity<UserResponseDto> updateUserInfo(@PathVariable String username, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserResponseDto userResponseDto = this.userService.getUserByUsername(username);
        boolean isEnabledUsername =  this.userService.userNameExists(userUpdateDto.getUsername());
        boolean isEnabledEmail = this.userService.emailExists(userUpdateDto.getEmail());

        if (userResponseDto == null) return ResponseEntity.notFound().build();
        if (!userResponseDto.getUsername().equals(userUpdateDto.getUsername()) && isEnabledUsername) return ResponseEntity.badRequest().build();
        if (!userResponseDto.getEmail().equals(userUpdateDto.getEmail()) && isEnabledEmail) return ResponseEntity.badRequest().build();

        this.userService.updateUser(userResponseDto.getId(), userUpdateDto);
        return new ResponseEntity<>(this.userService.getUserByUsername(username), CREATED);
    }
}
