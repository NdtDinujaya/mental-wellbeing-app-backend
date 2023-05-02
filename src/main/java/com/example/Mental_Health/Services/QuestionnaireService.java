package com.example.Mental_Health.Services;

import com.example.Mental_Health.Models.Questionnaire;
import com.example.Mental_Health.Models.Recommendation;
import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Repositories.QuestionnaireRepository;
import com.example.Mental_Health.Repositories.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public List<Recommendation> findAll() {
        return recommendationRepository.findAll();
    }

    public Recommendation findById(Long id) {
        return recommendationRepository.findById(id).orElse(null);
    }

    public Recommendation createRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> generateRecommendationsForUser(User user) {
        Map<String, List<Recommendation>> recommendationsByType = new HashMap<>();
        recommendationsByType.put("podcasts", generatePodcastRecommendations(user));
        recommendationsByType.put("movies", generateMovieRecommendations(user));
        recommendationsByType.put("educational_games", generateEducationalGameRecommendations(user));
        recommendationsByType.put("consultations", generateConsultationRecommendations(user));

        List<Recommendation> result = new ArrayList<>();
        recommendationsByType.values().forEach(result::addAll);
        return result;
    }

    private List<Recommendation> generatePodcastRecommendations(User user) {
        // Implement logic to generate podcast recommendations based on user's current status and goals
        // This could involve filtering from a predefined list of podcasts or using an external API
        return new ArrayList<>();
    }

    private List<Recommendation> generateMovieRecommendations(User user) {
        // Implement logic to generate movie recommendations based on user's current status and goals
        // This could involve filtering from a predefined list of movies or using an external API
        return new ArrayList<>();
    }

    private List<Recommendation> generateEducationalGameRecommendations(User user) {
        // Implement logic to generate educational game recommendations based on user's current status and goals
        // This could involve filtering from a predefined list of educational games or using an external API
        return new ArrayList<>();
    }

    private List<Recommendation> generateConsultationRecommendations(User user) {
        // Implement logic to generate consultation recommendations based on user's current status and goals
        // This could involve filtering from a predefined list of doctors or using an external API
        return new ArrayList<>();
    }

    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire findByUserId(Long userId) {
        return questionnaireRepository.findByUserId(userId);
    }
}
