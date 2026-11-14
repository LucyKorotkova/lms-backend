package com.example.lms.service;

import com.example.lms.dto.request.QuizPassDto;
import com.example.lms.dto.response.QuizResultDto;
import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.model.*;
import com.example.lms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public QuizResultDto passQuiz(Long quizId, QuizPassDto dto) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Тест не найден"));

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));

        // Упрощенный расчет результата (100 баллов за прохождение)
        int score = 100;

        QuizSubmission submission = QuizSubmission.builder()
                .quiz(quiz)
                .student(student)
                .score(score)
                .passedAt(LocalDateTime.now())
                .build();

        quizSubmissionRepository.save(submission);

        return QuizResultDto.builder()
                .quizId(quizId)
                .score(score)
                .message("Тест успешно пройден!")
                .build();
    }
}