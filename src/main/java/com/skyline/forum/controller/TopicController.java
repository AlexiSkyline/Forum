package com.skyline.forum.controller;

import com.skyline.forum.dto.mapper.ICourseMapper;
import com.skyline.forum.dto.mapper.IUserMapper;
import com.skyline.forum.dto.topic.*;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.model.Topic;
import com.skyline.forum.security.jwt.JwtUtils;
import com.skyline.forum.service.interfaces.ICourseService;
import com.skyline.forum.service.interfaces.ITopicService;
import com.skyline.forum.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/topics")
public class TopicController {
    private final ITopicService topicService;
    private final ICourseService courseService;
    private final IUserService userService;
    private final IUserMapper userMapper;
    private final ICourseMapper courseMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> createTopic(@RequestBody @Valid TopicRequestDto topicRequestDto) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        if (this.topicService.titleTopicExists(topicRequestDto.getTitle())) return ResponseEntity.badRequest().build();
        if (this.topicService.messageTopicExists(topicRequestDto.getMessage())) return ResponseEntity.badRequest().build();
        if (this.courseService.getCourseById(topicRequestDto.getCourse().getId()) == null) return ResponseEntity.notFound().build();

        topicService.saveTopic(Topic.builder()
                        .title(topicRequestDto.getTitle())
                        .message(topicRequestDto.getMessage())
                        .status(topicRequestDto.getStatus())
                        .author(this.userMapper.userResponseDtoToUser(user))
                        .course(this.courseMapper.topicCourseDtoToCourse(topicRequestDto.getCourse()))
                        .dateCreated(LocalDateTime.now())
                        .build());

        return new ResponseEntity<>(OK);
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDto>> getTopics() {
        return new ResponseEntity<>(this.topicService.getTopics(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicResponseDto> getTopicByID(@PathVariable Long id) {
        Optional<TopicResponseDto> topicFound = this.topicService.getTopicById(id);

        return topicFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updateTopic(@PathVariable Long id, @RequestBody TopicRequestDto topicRequestDto) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        Optional<TopicResponseDto> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return ResponseEntity.notFound().build();

        if (!topicFound.get().getAuthor().getUsername().equals(user.getUsername())) return new ResponseEntity<>(UNAUTHORIZED);
        if (!topicRequestDto.getTitle().equals(topicRequestDto.getTitle()) && this.topicService.titleTopicExists(topicRequestDto.getTitle())) return ResponseEntity.badRequest().build();
        if (!topicRequestDto.getMessage().equals(topicRequestDto.getMessage()) && this.topicService.messageTopicExists(topicRequestDto.getMessage())) return ResponseEntity.badRequest().build();
        if (this.courseService.getCourseById(topicRequestDto.getCourse().getId()) == null) return ResponseEntity.notFound().build();

        this.topicService.updateTopic(Topic.builder()
                        .id(id)
                        .title(topicRequestDto.getTitle())
                        .message(topicRequestDto.getMessage())
                        .status(topicRequestDto.getStatus())
                        .course(this.courseMapper.topicCourseDtoToCourse(topicRequestDto.getCourse()))
                        .build());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long id) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        Optional<TopicResponseDto> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return ResponseEntity.notFound().build();

        this.topicService.deleteTopicById(id);

        return ResponseEntity.ok().build();
    }
}
