package com.example.lms.controller;

import com.example.lms.dto.request.QuizPassDto;
import com.example.lms.dto.response.QuizResultDto;
import com.example.lms.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/{id}/pass")
    public ResponseEntity<QuizResultDto> passQuiz(
            @PathVariable Long id,
            @Valid @RequestBody QuizPassDto dto) {
        return ResponseEntity.ok(quizService.passQuiz(id, dto));
    }
}