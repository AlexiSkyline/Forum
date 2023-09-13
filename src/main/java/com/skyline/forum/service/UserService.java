package com.skyline.forum.service;

import com.skyline.forum.dto.mapper.IUserMapper;
import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.dto.user.UserUpdateDto;
import com.skyline.forum.model.User;
import com.skyline.forum.repository.IUserRepository;
import com.skyline.forum.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    @Transactional
    public void saveUser(SignupRequestDto signupRequestDto) {
        User user = this.userMapper.signupRequestDtoToUser(signupRequestDto);
        this.userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return this.userRepository.findAll().stream().map(this.userMapper::userToUserResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).map(this.userMapper::userToUserResponseDto).orElse(null);
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) return;
        this.userRepository.save(this.userMapper.userUpdateDtoToUser(user.get(), userUpdateDto));
    }

    @Override
    @Transactional
    public void deleteCourseById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean userNameExists(String username) {
        return this.userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        return this.userRepository.existsByEmailIgnoreCase(email);
    }
}
