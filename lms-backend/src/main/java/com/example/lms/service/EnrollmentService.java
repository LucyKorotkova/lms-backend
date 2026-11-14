package com.example.lms.service;

import com.example.lms.dto.request.EnrollmentRequestDto;
import com.example.lms.exception.BadRequestException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.model.Course;
import com.example.lms.model.Enrollment;
import com.example.lms.model.User;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.EnrollmentRepository;
import com.example.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public Enrollment enrollStudent(EnrollmentRequestDto dto) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId())) {
            throw new BadRequestException("Студент уже записан на данный курс");
        }

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Курс не найден"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .status(Enrollment.EnrollmentStatus.ACTIVE)
                .build();

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenrollStudent(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Запись на курс не найдена"));
        enrollmentRepository.delete(enrollment);
    }

    public List<Course> getStudentCourses(Long studentId) {
        return enrollmentRepository.findCoursesByStudentId(studentId);
    }
}