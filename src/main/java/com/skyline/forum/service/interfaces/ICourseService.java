package com.skyline.forum.service.interfaces;

import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.dto.course.CourseRequestDto;

import java.util.List;

public interface ICourseService {
    void saveCourse(CourseRequestDto courseRequestDto);
    List<CourseResponseDto> getCourses();
    CourseResponseDto getCourseById(Long id);
    void updateCourse(Long id, CourseRequestDto courseRequestDto);
    void deleteCourseById(Long id);

    boolean courseNameExists(String name);
}
