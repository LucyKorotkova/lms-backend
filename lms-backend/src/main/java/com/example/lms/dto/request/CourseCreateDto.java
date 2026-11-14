package com.example.lms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseCreateDto {
    @NotBlank(message = "Название курса не может быть пустым")
    private String title;

    private String description;

    @NotNull(message = "ID преподавателя обязателен")
    private Long teacherId;

    private Long categoryId;
}