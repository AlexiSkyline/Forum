package com.skyline.forum.service;

import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.dto.course.CourseRequestDto;
import com.skyline.forum.dto.mapper.ICourseMapper;
import com.skyline.forum.model.Course;
import com.skyline.forum.repository.ICourseRepository;
import com.skyline.forum.service.interfaces.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    private final ICourseRepository curseRepository;
    private final ICourseMapper courseMapper;

    @Override
    @Transactional
    public void saveCourse(CourseRequestDto courseRequestDto) {
        Course course = this.courseMapper.courseRequestDtoToCurse(courseRequestDto);
        this.curseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getCourses() {
        return this.curseRepository.findAll().stream().map(this.courseMapper::curseToCurseResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long id) {
        return this.curseRepository.findById(id).map(this.courseMapper::curseToCurseResponseDto).orElse(null);
    }

    @Override
    @Transactional
    public void updateCourse(Long id, CourseRequestDto courseRequestDto) {
        Course course = this.courseMapper.courseRequestDtoToCurse(courseRequestDto);
        course.setId(id);
        this.curseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(Long id) {
        this.curseRepository.deleteById(id);
    }

    @Override
    public boolean courseNameExists(String name) {
        return this.curseRepository.existsCourseByNameIgnoreCase(name);
    }
}
