package com.example.Mental_Health.Repositories;

import com.example.Mental_Health.Models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserId(Long userId);
    List<Recommendation> findByTypeAndIssue(String type, String issue);
    List<Recommendation> findByIssue(String issue);
    List<Recommendation> findByGoal(String goal);

}
