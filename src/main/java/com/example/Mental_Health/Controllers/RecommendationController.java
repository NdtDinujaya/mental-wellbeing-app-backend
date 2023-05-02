package com.example.Mental_Health.Controllers;

import com.example.Mental_Health.Models.Recommendation;
import com.example.Mental_Health.Models.RecommendationsAndAvatar;
import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Services.RecommendationService;
import com.example.Mental_Health.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Recommendation> findAll() {
        return recommendationService.findAll();
    }

    @GetMapping("/{id}")
    public Recommendation findById(@PathVariable Long id) {
        return recommendationService.findById(id);
    }

    @PostMapping
    public Recommendation create(@RequestBody Recommendation recommendation) {
        return recommendationService.save(recommendation);
    }

    @PutMapping("/{id}")
    public Recommendation update(@PathVariable Long id, @RequestBody Recommendation updatedRecommendation) {
        Recommendation recommendation = recommendationService.findById(id);
        // Update fields in the recommendation object
        // ...
        return recommendationService.save(recommendation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recommendationService.deleteById(id);
    }

//    @GetMapping("/generate/{userId}")
//    public ResponseEntity<List<Recommendation>> generateRecommendationsForUser(@PathVariable Long userId) {
//        User user = userService.findById(userId);
//        if (user == null) {
//            System.out.println("Hello");
//            return ResponseEntity.notFound().build();
//        }
//
//        List<Recommendation> recommendations = recommendationService.generateRecommendationsForUser(user);
//        System.out.println(recommendations);
//        return ResponseEntity.ok(recommendations);
//    }

//    @PostMapping("/{userId}/recommendations")
//    public ResponseEntity<List<Recommendation>> generateRecommendations(@PathVariable Long userId) {
//        List<Recommendation> recommendations = recommendationService.generateRecommendationsForUser(userId);
//        return new ResponseEntity<>(recommendations, HttpStatus.OK);
//    }

//    @GetMapping("/generate/{userId}")
//    public ResponseEntity<RecommendationsAndAvatar> generateRecommendationsForUser(@PathVariable Long userId) {
//        User user = userService.findById(userId);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        List<Recommendation> recommendations = recommendationService.generateRecommendationsForUser(user);
//        String avatar = userService.assignAvatar(user);
//
//        RecommendationsAndAvatar recommendationsAndAvatar = new RecommendationsAndAvatar(recommendations, avatar);
//        return ResponseEntity.ok(recommendationsAndAvatar);
//    }

    @GetMapping("/generate/{userId}")
    public ResponseEntity<RecommendationsAndAvatar> generateRecommendationsForUser(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Recommendation> recommendations = recommendationService.generateRecommendationsForUser(user);
        String avatar = userService.assignAvatar(user); // use the returned avatar

        RecommendationsAndAvatar recommendationsAndAvatar = new RecommendationsAndAvatar(recommendations, avatar);
        return ResponseEntity.ok(recommendationsAndAvatar);
    }

    @PostMapping("/generate/{userId}")
    public ResponseEntity<RecommendationsAndAvatar> generateRecommendationsForUsers(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Recommendation> recommendations = recommendationService.generateRecommendationsForUser(user);
        String avatar = userService.assignAvatar(user); // use the returned avatar

        RecommendationsAndAvatar recommendationsAndAvatar = new RecommendationsAndAvatar(recommendations, avatar);
        return ResponseEntity.ok(recommendationsAndAvatar);
    }

}

