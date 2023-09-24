package com.skyline.forum;

import com.skyline.forum.dto.mapper.ICourseMapper;
import com.skyline.forum.dto.mapper.IUserMapper;
import com.skyline.forum.dto.topic.TopicCourseDto;
import com.skyline.forum.dto.topic.TopicRequestDto;
import com.skyline.forum.dto.user.SignupRequestDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.dto.user.UserUpdateDto;
import com.skyline.forum.model.Topic;
import com.skyline.forum.model.User;
import com.skyline.forum.model.enums.StatusTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForumAluraApplicationTests {
	@Autowired
	private IUserMapper userMapper;
	@Autowired
	private ICourseMapper courseMapper;

	@Test
	void contextLoads() {
		TopicRequestDto topicRequestDto = new TopicRequestDto();

		topicRequestDto.setTitle("Error to mapper my entity");
		topicRequestDto.setStatus(StatusTopic.CLOSED);
		topicRequestDto.setMessage("My entity mark all filed null when try used mapper");

		TopicCourseDto courseDto = new TopicCourseDto();
		courseDto.setId(1L);
		courseDto.setName("Introduction java");
		courseDto.setCategory("Programming");

		topicRequestDto.setCourse(courseDto);

		UserResponseDto user = new UserResponseDto();
		user.setId(23L);
		user.setUsername("Alexskyine");
		user.setEmail("hola@hotmail.com");

		Topic newTopic = Topic.builder()
				.title(topicRequestDto.getTitle())
				.message(topicRequestDto.getMessage())
				.status(topicRequestDto.getStatus())
				.author(this.userMapper.userResponseDtoToUser(user))
				.course(this.courseMapper.topicCourseDtoToCourse(topicRequestDto.getCourse()))
				.build();

		System.out.println(newTopic.getId());
		System.out.println(newTopic.getTitle());
		System.out.println(newTopic.getMessage());
		System.out.println("+===========================");
		System.out.println(newTopic.getCourse().getId());
		System.out.println(newTopic.getCourse().getName());
		System.out.println(newTopic.getCourse().getCategory());
		System.out.println("===============================");
		System.out.println(newTopic.getAuthor().getId());
		System.out.println(newTopic.getAuthor().getUsername());
		System.out.println(newTopic.getAuthor().getEmail());
	}

}
