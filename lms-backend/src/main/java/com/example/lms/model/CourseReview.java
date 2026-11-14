package com.example.lms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_reviews")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CourseReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating; // 1-5
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}