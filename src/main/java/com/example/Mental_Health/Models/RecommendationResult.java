package com.example.Mental_Health.Models;

import java.util.List;

public class RecommendationResult {
    private List<Recommendation> recommendations;
    private String avatar;

    public RecommendationResult(List<Recommendation> recommendations, String avatar) {
        this.recommendations = recommendations;
        this.avatar = avatar;
    }
}