package com.example.Mental_Health.Models;

import jakarta.persistence.*;


@Entity
@Table(name = "questionnaire_issues")
public class QuestionnaireIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @Column(nullable = false)
    private String issue;

    // Getters and setters
}
