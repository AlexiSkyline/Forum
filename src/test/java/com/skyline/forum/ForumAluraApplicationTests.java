package com.skyline.forum;

import com.skyline.forum.dto.mapper.IUserMapper;
import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserUpdateDto;
import com.skyline.forum.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForumAluraApplicationTests {
	@Autowired
	private IUserMapper userMapper;

	@Test
	void contextLoads() {
		UserUpdateDto updateUser = new UserUpdateDto();
		updateUser.setUsername("JunitoSkyline");
		updateUser.setEmail("JunitoSkyline@Hotmail.com");

		User userPersisted = new User();
		userPersisted.setId(899L);
		userPersisted.setUsername("Alexiskyline");
		userPersisted.setEmail("Alexiskyline@hotmail.com");
		userPersisted.setPassword("Hola99");

		User userChanges = this.userMapper.userUpdateDtoToUser(userPersisted, updateUser);
		System.out.println(userChanges.getId());
		System.out.println(userChanges.getUsername());
		System.out.println(userChanges.getEmail());
		System.out.println(userChanges.getPassword());
	}

}
