package com.interviewManagementApplication.RMS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewManagementApplication.RMS.model.Comment;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class CommentAttributeConverter implements AttributeConverter<Comment, String> {

    private static final Logger logger = LoggerFactory.getLogger(CommentAttributeConverter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Comment comment) {
        try {
            return objectMapper.writeValueAsString(comment);
        } catch (JsonProcessingException e) {
            logger.error("Error converting Comment to JSON: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Comment convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Comment.class);
        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to Comment: {}", e.getMessage());
            return null;
        }
    }
}
