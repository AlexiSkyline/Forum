package com.skyline.forum.controller;

import com.skyline.forum.model.Topic;
import com.skyline.forum.service.interfaces.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/topics")
public class TopicController {
    private final ITopicService topicService;

    @PostMapping
    public ResponseEntity createTopic(@RequestBody Topic topic) {
        topicService.saveTopic(topic);

        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics() {
        return new ResponseEntity<>(this.topicService.getTopics(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Topic> getTopicByID(@PathVariable Long id) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);

        return topicFound.map(topic -> new ResponseEntity<>(topic, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.topicService.updateTopic(topic);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTopicById(@PathVariable Long id) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        this.topicService.deleteTopicById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
