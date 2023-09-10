package com.skyline.forum.service.interfaces;

import com.skyline.forum.model.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicService {
    void saveTopic(Topic topic);
    List<Topic> getTopics();
    Optional<Topic> getTopicById(Long id);
    void updateTopic(Topic topic);
    void deleteTopicById(Long id);
}
