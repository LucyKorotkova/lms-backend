package com.example.lms.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResultDto {
    private Long quizId;
    private Integer score;
    private String message;
}