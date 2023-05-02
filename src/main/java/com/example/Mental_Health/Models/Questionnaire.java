package com.example.Mental_Health.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "questionnaire_issues", joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "issue")
    private List<String> issues;

    @ElementCollection
    @CollectionTable(name = "questionnaire_goals", joinColumns = @JoinColumn(name = "questionnaire_id"))
    @Column(name = "goal")
    private List<String> goals;

    public List<String> getIssues() {
        return issues;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
