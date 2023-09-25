package com.skyline.forum.dto.answer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AnswerRequestDto {
    @NotBlank
    private String message;
}
