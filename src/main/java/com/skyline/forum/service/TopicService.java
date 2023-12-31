package com.skyline.forum.service;

import com.skyline.forum.dto.mapper.ITopicMapper;
import com.skyline.forum.dto.topic.TopicResponseDto;
import com.skyline.forum.model.Answer;
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
    private final ITopicMapper topicMapper;

    @Override
    @Transactional
    public void saveTopic(Topic topic) {
        this.topicRepository.save(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicResponseDto> getTopics() {
        return this.topicRepository.findAll().stream().map(this.topicMapper::topicToTopicResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TopicResponseDto> getTopicById(Long id) {
        return this.topicRepository.findById(id).map(this.topicMapper::topicToTopicResponseDto);
    }

    @Override
    @Transactional
    public void updateTopic(Topic topic) {
        Optional<Topic> topicFound = this.topicRepository.findById(topic.getId());

        if (topicFound.isEmpty()) return;
        topicFound.get().setTitle(topic.getTitle());
        topicFound.get().setMessage(topic.getMessage());
        topicFound.get().setStatus(topic.getStatus());
        topicFound.get().setCourse(topic.getCourse());

        this.topicRepository.save(topicFound.get());
    }

    @Override
    @Transactional
    public void deleteTopicById(Long id) {
        this.topicRepository.deleteById(id);
    }

    @Override
    public boolean titleTopicExists(String title) {
        return this.topicRepository.existsTopicByTitleIgnoreCase(title);
    }

    @Override
    public boolean messageTopicExists(String message) {
        return this.topicRepository.existsTopicByMessageIgnoreCase(message);
    }

    @Override
    @Transactional
    public void setAnswer(Long idTopic, Answer answer) {
        Optional<Topic> topicFound = this.topicRepository.findById(idTopic);
        if (topicFound.isEmpty()) return;

        answer.setTopic(topicFound.get());
        topicFound.get().getAnswers().add(answer);
        this.topicRepository.save(topicFound.get());
    }
}
