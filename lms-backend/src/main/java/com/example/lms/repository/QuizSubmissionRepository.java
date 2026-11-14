package com.example.lms.repository;

import com.example.lms.model.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
}