package com.example.lms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignmentSubmitDto {
    @NotNull
    private Long studentId;

    @NotBlank(message = "Текст решения не может быть пустым")
    private String solutionText;
}