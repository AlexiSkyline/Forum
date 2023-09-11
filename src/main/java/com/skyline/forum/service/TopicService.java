package com.skyline.forum.service;

import com.skyline.forum.model.Topic;
import com.skyline.forum.repository.ITopicRepository;
import com.skyline.forum.service.interfaces.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {

    private final ITopicRepository topicRepository;

    @Override
    @Transactional
    public void saveTopic(Topic topic) {
        this.topicRepository.save(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getTopics() {
        return this.topicRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Topic> getTopicById(Long id) {
        return this.topicRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateTopic(Topic topic) {
        Optional<Topic> topicFound = this.topicRepository.findById(topic.getId());

        if (topicFound.isEmpty()) return;
        topicFound.get().setTitle(topic.getTitle());
        topicFound.get().setMessage(topic.getMessage());
        topicFound.get().setStatus(topic.getStatus());

        this.topicRepository.save(topicFound.get());
    }

    @Override
    @Transactional
    public void deleteTopicById(Long id) {
        this.topicRepository.deleteById(id);
    }
}
