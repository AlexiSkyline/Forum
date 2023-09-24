package com.skyline.forum.service.interfaces;

import com.skyline.forum.dto.topic.TopicResponseDto;
import com.skyline.forum.model.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicService {
    void saveTopic(Topic topic);
    List<TopicResponseDto> getTopics();
    Optional<TopicResponseDto> getTopicById(Long id);
    void updateTopic(Topic topic);
    void deleteTopicById(Long id);

    boolean titleTopicExists(String title);
    boolean messageTopicExists(String message);
}
