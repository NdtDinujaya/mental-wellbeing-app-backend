package com.example.Mental_Health.Controllers;

import com.example.Mental_Health.Models.ConcernAndGoal;
import com.example.Mental_Health.Models.SurveyResponse;
import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Repositories.UserRepository;
import com.example.Mental_Health.Services.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/surveyresponses")
    public ResponseEntity<SurveyResponse> saveSurveyResponse(@RequestBody SurveyResponse surveyResponse) {
        SurveyResponse savedSurveyResponse = surveyResponseService.saveSurveyResponse(surveyResponse);
        return new ResponseEntity<>(savedSurveyResponse, HttpStatus.CREATED);
    }

    @PostMapping("/surveyresponses/{userId}")
    public ResponseEntity<SurveyResponse> saveSurveyResponse(@PathVariable Long userId, @RequestBody SurveyResponse surveyResponse) {
        // Retrieve the user from the database
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = optionalUser.get();

        // Set avatar based on the issues and goals
        Map<ConcernAndGoal, String> avatarMapping = new HashMap<>();
        avatarMapping.put(new ConcernAndGoal("Inadequacy about your life or appearance", "Improve Mental Health"), "avatar1");
        avatarMapping.put(new ConcernAndGoal("Fear of missing out (FOMO)", "Distract from Social Media"), "avatar2");
        // Add more mappings here.

        // Assuming the user has selected only one concern and one goal.
        ConcernAndGoal userConcernAndGoal = new ConcernAndGoal(surveyResponse.getIssues().get(0), surveyResponse.getGoals().get(0));
        String avatar = avatarMapping.getOrDefault(userConcernAndGoal, "defaultAvatar");
        user.setAvatar(avatar);

        // Save the updated user to the database
        userRepository.save(user);

        // Save the survey response
        SurveyResponse savedSurveyResponse = surveyResponseService.saveSurveyResponse(surveyResponse);
        return new ResponseEntity<>(savedSurveyResponse, HttpStatus.CREATED);
    }

}