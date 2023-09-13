package com.skyline.forum.service.interfaces;

import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.dto.user.UserUpdateDto;

import java.util.List;

public interface IUserService {
    void saveUser(SignupRequestDto signupRequestDto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserByUsername(String username);
    void updateUser(Long id, UserUpdateDto userUpdateDto);
    void deleteCourseById(Long id);

    boolean userNameExists(String username);
    boolean emailExists(String email);
}
