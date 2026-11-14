package com.example.lms.controller;

import com.example.lms.dto.request.AssignmentSubmitDto;
import com.example.lms.model.AssignmentSubmission;
import com.example.lms.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/{id}/submit")
    public ResponseEntity<AssignmentSubmission> submitAssignment(
            @PathVariable Long id,
            @Valid @RequestBody AssignmentSubmitDto dto) {
        return new ResponseEntity<>(assignmentService.submitAssignment(id, dto), HttpStatus.CREATED);
    }
}