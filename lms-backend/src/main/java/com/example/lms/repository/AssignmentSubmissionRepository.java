package com.example.lms.repository;

import com.example.lms.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    boolean existsByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
    Optional<AssignmentSubmission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}