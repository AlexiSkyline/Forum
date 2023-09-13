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
@RequestMapping("v1/api/topics")
public class TopicController {
    private final ITopicService topicService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTopic(@RequestBody Topic topic) {
        topicService.saveTopic(topic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getTopics() {
        return new ResponseEntity<>(this.topicService.getTopics(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Topic> getTopicByID(@PathVariable Long id) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);

        return topicFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return ResponseEntity.notFound().build();

        this.topicService.updateTopic(topic);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long id) {
        Optional<Topic> topicFound = this.topicService.getTopicById(id);
        if (topicFound.isEmpty()) return ResponseEntity.notFound().build();

        this.topicService.deleteTopicById(id);

        return ResponseEntity.ok().build();
    }
}
