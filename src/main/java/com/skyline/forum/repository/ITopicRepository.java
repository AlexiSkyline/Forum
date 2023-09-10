package com.skyline.forum.repository;

import com.skyline.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepository extends JpaRepository<Topic, Long> {}
