package com.skyline.forum.dto.mapper;

import com.skyline.forum.dto.course.CourseResponseDto;
import com.skyline.forum.dto.course.CourseRequestDto;
import com.skyline.forum.dto.topic.TopicCourseDto;
import com.skyline.forum.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ICourseMapper {
    Course courseRequestDtoToCurse(CourseRequestDto courseRequestDto);

    Course curseResponseDtoToCurse(CourseResponseDto courseResponseDto);
    CourseResponseDto curseToCurseResponseDto(Course course);

    Course topicCourseDtoToCourse(TopicCourseDto topicCourseDto);
}
