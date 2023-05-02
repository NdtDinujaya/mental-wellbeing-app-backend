package com.example.Mental_Health.Controllers;

// QuestionnaireController.java

import com.example.Mental_Health.Models.Questionnaire;
import com.example.Mental_Health.Services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaires")
@CrossOrigin
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        return new ResponseEntity<>(questionnaireService.getAllQuestionnaires(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        Questionnaire createdQuestionnaire = questionnaireService.createQuestionnaire(questionnaire);
        return ResponseEntity.ok(createdQuestionnaire);
    }

    // Implement other API endpoints for handling questionnaires
}
