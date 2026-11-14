package com.example.lms.service;

import com.example.lms.dto.request.AssignmentSubmitDto;
import com.example.lms.exception.BadRequestException;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.model.*;
import com.example.lms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public AssignmentSubmission submitAssignment(Long assignmentId, AssignmentSubmitDto dto) {
        if (submissionRepository.existsByAssignmentIdAndStudentId(assignmentId, dto.getStudentId())) {
            throw new BadRequestException("Вы уже отправляли решение на это задание");
        }

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Задание не найдено"));

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));

        AssignmentSubmission submission = AssignmentSubmission.builder()
                .assignment(assignment)
                .student(student)
                .solutionText(dto.getSolutionText())
                .submittedAt(LocalDateTime.now())
                .build();

        return submissionRepository.save(submission);
    }
}