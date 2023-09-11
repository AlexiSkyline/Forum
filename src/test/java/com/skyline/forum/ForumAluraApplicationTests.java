package com.skyline.forum;

import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.dto.mapper.ICourseMapper;
import com.skyline.forum.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForumAluraApplicationTests {
	@Autowired
	private ICourseMapper courseMapper;

	@Test
	void contextLoads() {
		Course curse = new Course();
		curse.setId(1L);
		curse.setName("Laratube");
		curse.setCategory("Programming");

		CourseResponseDto courseResponseDto = this.courseMapper.curseToCurseResponseDto(curse);
		System.out.println(courseResponseDto.getId());
		System.out.println(courseResponseDto.getName());
		System.out.println(courseResponseDto.getCategory());

	}

}
