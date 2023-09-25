package com.skyline.forum.controller;


import com.skyline.forum.dto.answer.AnswerRequestDto;
import com.skyline.forum.dto.topic.TopicResponseDto;
import com.skyline.forum.dto.user.UserResponseDto;
import com.skyline.forum.model.Answer;
import com.skyline.forum.security.jwt.JwtUtils;
import com.skyline.forum.service.interfaces.IAnswerService;
import com.skyline.forum.service.interfaces.ITopicService;
import com.skyline.forum.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/answers")
@SecurityRequirement(name = "JWT Bearer Token")
public class AnswerController {
    private final IAnswerService answerService;
    private final IUserService userService;
    private final ITopicService topicService;

    @PutMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updateAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto answerRequestDto) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        Optional<Answer> answer = this.answerService.getAnswer(id);
        if (answer.isEmpty()) return ResponseEntity.notFound().build();
        if (!answer.get().getAuthor().getUsername().equals(user.getUsername())) return new ResponseEntity<>(UNAUTHORIZED);

        this.answerService.updateMessage(id, answerRequestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        Optional<Answer> answer = this.answerService.getAnswer(id);
        if (answer.isEmpty()) return ResponseEntity.notFound().build();
        if (!answer.get().getAuthor().getUsername().equals(user.getUsername())) return new ResponseEntity<>(UNAUTHORIZED);

        this.answerService.deleteAnswer(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("set-solution/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> setSolution(@PathVariable Long id) {
        UserResponseDto user = this.userService.getUserByUsername(JwtUtils.getUserNameOfToken());
        if (user == null) return new ResponseEntity<>(UNAUTHORIZED);

        Optional<Answer> answer = this.answerService.getAnswer(id);
        if (answer.isEmpty()) return ResponseEntity.notFound().build();

        Optional<TopicResponseDto> topicFound = this.topicService.getTopicById(answer.get().getTopic().getId());
        if (topicFound.isEmpty()) return ResponseEntity.notFound().build();

        if (!topicFound.get().getAuthor().getUsername().equals(user.getUsername())) return new ResponseEntity<>(UNAUTHORIZED);

        this.answerService.setAsSolution(id);

        return ResponseEntity.ok().build();
    }
}
