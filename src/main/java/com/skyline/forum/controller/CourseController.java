package com.skyline.forum.controller;

import com.skyline.forum.dto.course.CourseRequestDto;
import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.service.interfaces.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/courses")
public class CourseController {
    private final ICourseService courseService;

    @PostMapping
    public ResponseEntity<Void> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        boolean nameAvailable = this.courseService.courseNameExists(courseRequestDto.getName());
        if (nameAvailable) return ResponseEntity.badRequest().build();

        this.courseService.saveCourse(courseRequestDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return new ResponseEntity<>(this.courseService.getCourses(), OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        CourseResponseDto courseResponseDto = this.courseService.getCourseById(id);

        if (courseResponseDto == null) return ResponseEntity.notFound().build();

        return new ResponseEntity<>(courseResponseDto, OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto courseResponseDto = this.courseService.getCourseById(id);
        if (courseResponseDto == null) ResponseEntity.notFound().build();

        boolean nameAvailable = this.courseService.courseNameExists(courseRequestDto.getName());
        if (!courseResponseDto.getName().equals(courseRequestDto.getName()) && nameAvailable) return ResponseEntity.badRequest().build();

        this.courseService.updateCourse(id, courseRequestDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        CourseResponseDto courseResponseDto = this.courseService.getCourseById(id);
        if (courseResponseDto == null) return ResponseEntity.notFound().build();
        this.courseService.deleteCourseById(id);

        return ResponseEntity.ok().build();
    }
}
