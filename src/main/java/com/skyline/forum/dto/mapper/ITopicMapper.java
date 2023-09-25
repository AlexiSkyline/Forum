package com.skyline.forum.dto.mapper;


import com.skyline.forum.dto.topic.TopicAnswerDto;
import com.skyline.forum.dto.topic.TopicResponseDto;
import com.skyline.forum.model.Answer;
import com.skyline.forum.model.Topic;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ITopicMapper {
    TopicResponseDto topicToTopicResponseDto(Topic topic);

    Set<TopicAnswerDto> mapAnswerList(Set<Answer> answers);
}
