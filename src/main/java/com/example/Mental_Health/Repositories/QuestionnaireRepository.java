package com.example.Mental_Health.Repositories;

import com.example.Mental_Health.Models.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    Questionnaire findByUserId(Long userId);

}
