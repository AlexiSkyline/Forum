package com.skyline.forum.repository;

import com.skyline.forum.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {}
