package com.skyline.forum.repository;

import com.skyline.forum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    Boolean existsCourseByNameIgnoreCase(String name);
}
