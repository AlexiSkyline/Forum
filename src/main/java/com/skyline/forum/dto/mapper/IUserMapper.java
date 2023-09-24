package com.skyline.forum.dto.mapper;

import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.dto.user.UserUpdateDto;
import com.skyline.forum.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface IUserMapper {
    User signupRequestDtoToUser(SignupRequestDto signupRequestDto);

    User userResponseDtoToUser(UserResponseDto userResponseDto);

    UserResponseDto userToUserResponseDto(User user);

    @Mapping(target = "username", source = "userUpdateDto.username")
    @Mapping(target = "email", source = "userUpdateDto.email")
    User userUpdateDtoToUser(User user, UserUpdateDto userUpdateDto);
}
