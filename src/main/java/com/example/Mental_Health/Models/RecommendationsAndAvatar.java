package com.example.Mental_Health.Models;

import java.util.List;

public class RecommendationsAndAvatar {
    private List<Recommendation> recommendations;
    private String avatar;

    public RecommendationsAndAvatar(List<Recommendation> recommendations, String avatar) {
        this.recommendations = recommendations;
        this.avatar = avatar;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}