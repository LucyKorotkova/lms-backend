package com.example.lms.controller;

import com.example.lms.dto.request.EnrollmentRequestDto;
import com.example.lms.model.Course;
import com.example.lms.model.Enrollment;
import com.example.lms.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@Valid @RequestBody EnrollmentRequestDto dto) {
        return new ResponseEntity<>(enrollmentService.enrollStudent(dto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> unenrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.unenrollStudent(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentCourses(studentId));
    }
}