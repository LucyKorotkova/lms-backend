package com.example.lms.service;

import com.example.lms.dto.request.CourseCreateDto;
import com.example.lms.dto.response.CourseResponseDto;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.model.Category;
import com.example.lms.model.Course;
import com.example.lms.model.User;
import com.example.lms.repository.CategoryRepository;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public CourseResponseDto createCourse(CourseCreateDto dto) {
        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Преподаватель не найден"));

        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        }

        Course course = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .teacher(teacher)
                .category(category)
                .build();

        Course saved = courseRepository.save(course);
        return mapToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Курс не найден: " + id));
        return mapToDto(course);
    }

    private CourseResponseDto mapToDto(Course course) {
        return CourseResponseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .teacherName(course.getTeacher() != null ? course.getTeacher().getFullName() : "Не указан")
                .categoryName(course.getCategory() != null ? course.getCategory().getName() : "Без категории")
                .build();
    }
}