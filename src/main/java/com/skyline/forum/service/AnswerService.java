package com.skyline.forum.service;

import com.skyline.forum.dto.answer.AnswerRequestDto;
import com.skyline.forum.model.Answer;
import com.skyline.forum.repository.IAnswerRepository;
import com.skyline.forum.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {
    private final IAnswerRepository answerRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Answer> getAnswer(Long id) {
        return this.answerRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateMessage(Long id, AnswerRequestDto answerRequestDto) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isEmpty()) return;
        answer.get().setMessage(answerRequestDto.getMessage());
        this.answerRepository.save(answer.get());
    }

    @Override
    @Transactional
    public void setAsSolution(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isEmpty()) return;
        answer.get().setSolution(true);
        this.answerRepository.save(answer.get());
    }

    @Override
    @Transactional
    public void deleteAnswer(Long id) {
        this.answerRepository.deleteById(id);
    }
}
