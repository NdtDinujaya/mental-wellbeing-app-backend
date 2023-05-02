package com.example.Mental_Health.Services;

import com.example.Mental_Health.Models.ConcernAndGoal;
import com.example.Mental_Health.Models.Questionnaire;
import com.example.Mental_Health.Models.Recommendation;
import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Repositories.QuestionnaireRepository;
import com.example.Mental_Health.Repositories.RecommendationRepository;
import com.example.Mental_Health.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Recommendation> findAll() {
        return recommendationRepository.findAll();
    }

    public Recommendation findById(Long id) {
        return recommendationRepository.findById(id).orElse(null);
    }

    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public void deleteById(Long id) {
        recommendationRepository.deleteById(id);
    }

    public List<Recommendation> generateRecommendationsForUser(User user) {
            // Access the user's questionnaire
            Questionnaire questionnaire = questionnaireRepository.findByUserId(user.getId());
            List<String> issues = questionnaire.getIssues();
            List<String> goals = questionnaire.getGoals();

            // Prepare a list to store recommendations
            List<Recommendation> recommendations = new ArrayList<>();

            // Generate recommendations based on issues
            for (String issue : issues) {
                List<Recommendation> issueRecommendations = recommendationRepository.findByIssue(issue);
                recommendations.addAll(issueRecommendations);
            }

            // Generate recommendations based on goals
            for (String goal : goals) {
                List<Recommendation> goalRecommendations = recommendationRepository.findByGoal(goal);
                recommendations.addAll(goalRecommendations);
            }

            // Remove duplicate recommendations
            recommendations = recommendations.stream()
                    .distinct()
                    .collect(Collectors.toList());

            // Assign an avatar to the user based on their concern and goal
            Map<ConcernAndGoal, String> avatarMapping = new HashMap<>();
            avatarMapping.put(new ConcernAndGoal("Inadequacy about your life or appearance", "Improve Mental Health"), "avatar1");
            avatarMapping.put(new ConcernAndGoal("Fear of missing out (FOMO)", "Distract from Social Media"), "avatar2");
            // Add more mappings here.

            // Assuming the user has selected only one concern and one goal.
            ConcernAndGoal userConcernAndGoal = new ConcernAndGoal(issues.get(0), goals.get(0));
            String avatar = avatarMapping.getOrDefault(userConcernAndGoal, "defaultAvatar");
            user.setAvatar(avatar);

        userRepository.save(user);

        // Return the list of recommendations
            return recommendations;
     //   }

//=====================

//        // Access the user's questionnaire
//        Questionnaire questionnaire = questionnaireRepository.findByUserId(user.getId());
//        List<String> issues = questionnaire.getIssues();
//
//        System.out.println(issues);
//
//
//        List<String> goals = questionnaire.getGoals();
//
//        System.out.println(goals);
//
//         //Prepare a list to store recommendations
//        List<Recommendation> recommendations = new ArrayList<>();
//
//        // Generate recommendations based on issues
//        for (String issue : issues) {
//            List<Recommendation> issueRecommendations = recommendationRepository.findByIssue(issue);
//            recommendations.addAll(issueRecommendations);
//        }
//
//        System.out.println(recommendations);
//
//        // Generate recommendations based on goals
//        for (String goal : goals) {
//            List<Recommendation> goalRecommendations = recommendationRepository.findByGoal(goal);
//            recommendations.addAll(goalRecommendations);
//        }
//
//        System.out.println(recommendations);
//
//        // Remove duplicate recommendations
//        recommendations = recommendations.stream()
//                .distinct()
//                .collect(Collectors.toList());
//
//        System.out.println(recommendations);
//        // Return the list of recommendations
//
//        return recommendations;

        //-------------------------------------

//        List<Recommendation> recommendations = new ArrayList<>();
//
//        for (String issue : questionnaire.getIssues()) {
//            List<Recommendation> issueRecommendations = recommendationRepository.findByIssue(issue);
//            System.out.println("Issue: " + issue + ", Recommendations: " + issueRecommendations); // Debug logging
//            recommendations.addAll(issueRecommendations);
//        }
//
//        for (String goal : questionnaire.getGoals()) {
//            List<Recommendation> goalRecommendations = recommendationRepository.findByGoal(goal);
//            System.out.println("Goal: " + goal + ", Recommendations: " + goalRecommendations); // Debug logging
//            recommendations.addAll(goalRecommendations);
//        }
//
//        return recommendations;
    }

}