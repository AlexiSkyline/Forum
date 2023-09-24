package com.skyline.forum.dto.mapper;


import com.skyline.forum.dto.topic.TopicResponseDto;
import com.skyline.forum.model.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ITopicMapper {
    TopicResponseDto topicToTopicResponseDto(Topic topic);
}
