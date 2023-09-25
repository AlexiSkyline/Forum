package com.skyline.forum.service.interfaces;

import com.skyline.forum.dto.answer.AnswerRequestDto;
import com.skyline.forum.model.Answer;

import java.util.Optional;

public interface IAnswerService {
    Optional<Answer> getAnswer(Long id);
    void updateMessage(Long id, AnswerRequestDto answerRequestDto);
    void setAsSolution(Long id);
    void deleteAnswer(Long id);

}
