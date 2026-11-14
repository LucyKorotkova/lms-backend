package com.example.lms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Map;

@Data
public class QuizPassDto {
    @NotNull
    private Long studentId;

    private Map<Long, Long> answers;
}