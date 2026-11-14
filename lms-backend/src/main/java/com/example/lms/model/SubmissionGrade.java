package com.example.lms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "submission_grades")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SubmissionGrade {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "submission_id")
    private AssignmentSubmission submission;

    private Integer score;
    private String feedback;
}