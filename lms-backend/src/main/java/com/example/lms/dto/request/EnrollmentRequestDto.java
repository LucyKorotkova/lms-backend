package com.example.lms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDto {
    @NotNull(message = "ID студента обязателен")
    private Long studentId;

    @NotNull(message = "ID курса обязателен")
    private Long courseId;
}