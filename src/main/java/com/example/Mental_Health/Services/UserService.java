package com.example.Mental_Health.Services;

// UserService.java

import com.example.Mental_Health.Models.Avatar;
import com.example.Mental_Health.Models.Questionnaire;
import com.example.Mental_Health.Models.User;
import com.example.Mental_Health.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Implement user creation logic here
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add other methods for handling user authentication and related functionalities

    @Autowired
    private QuestionnaireService questionnaireService;

//    public String assignAvatar(User user) {
//
//
//        // Get the user's questionnaire
//        Questionnaire questionnaire = questionnaireService.findByUserId(user.getId());
//
//        // Get the user's issues and goals
//        List<String> issues = questionnaire.getIssues();
//        List<String> goals = questionnaire.getGoals();
//
//        // Here, you can implement your custom logic to assign an avatar based on the user's mental state and recommendations.
//        // For example:
////        if (issues.contains("Stress") && goals.contains("Improve Mental Health")) {
////            user.setAvatar("avatar1");
////        } else if (issues.contains("Social Media Addiction") && goals.contains("Reduce Social Media Usage")) {
////            user.setAvatar("avatar2");
////        } else {
////            user.setAvatar("defaultAvatar");
////        }
//
//        Avatar assignedAvatar = Avatar.DEFAULT;
//
//        if (issues.contains("Stress") && goals.contains("Improve Mental Health")) {
//            assignedAvatar = Avatar.AVATAR1;
//        } else if (issues.contains("Social Media Addiction") && goals.contains("Reduce Social Media Usage")) {
//            assignedAvatar = Avatar.AVATAR2;
//        } else if (issues.contains("Anxiety") && goals.contains("Improve Coping Skills")) {
//            assignedAvatar = Avatar.AVATAR3;
//        } else if (issues.contains("Depression") && goals.contains("Increase Positive Mood")) {
//            assignedAvatar = Avatar.AVATAR4;
//        } else if (issues.contains("Insomnia") && goals.contains("Improve Sleep Quality")) {
//            assignedAvatar = Avatar.AVATAR5;
//        } else if (issues.contains("Low Self-esteem") && goals.contains("Boost Confidence")) {
//            assignedAvatar = Avatar.AVATAR6;
//        } else if (issues.contains("Anger Management") && goals.contains("Control Anger")) {
//            assignedAvatar = Avatar.AVATAR7;
//        }
//        user.setAvatar(assignedAvatar.getAvatarName());
//
//
//        // Update the user in the database
//        userRepository.save(user);
//        return null;
//    }


    public String assignAvatar(User user) {
        // Access the user's questionnaire
        Questionnaire questionnaire = questionnaireService.findByUserId(user.getId());
        List<String> issues = questionnaire.getIssues();
        List<String> goals = questionnaire.getGoals();

        // Define a HashMap to store combinations of issues and goals as keys, and their corresponding avatars as values
        Map<List<String>, Avatar> avatarMap = new HashMap<>();

        // Populate the HashMap with issue-goal combinations and their corresponding avatars
        avatarMap.put(Arrays.asList("Stress", "Improve Mental Health"), Avatar.AVATAR1);
        avatarMap.put(Arrays.asList("Social Media Addiction", "Reduce Social Media Usage"), Avatar.AVATAR2);
        avatarMap.put(Arrays.asList("Anxiety", "Improve Coping Skills"), Avatar.AVATAR3);
        avatarMap.put(Arrays.asList("Depression", "Increase Positive Mood"), Avatar.AVATAR4);
        avatarMap.put(Arrays.asList("Insomnia", "Improve Sleep Quality"), Avatar.AVATAR5);
        avatarMap.put(Arrays.asList("Low Self-esteem", "Boost Confidence"), Avatar.AVATAR6);
        avatarMap.put(Arrays.asList("Anger Management", "Control Anger"), Avatar.AVATAR7);

        // Set the default avatar
        Avatar assignedAvatar = Avatar.DEFAULT;

        // Assign the avatar based on the user's issues and goals
        for (Map.Entry<List<String>, Avatar> entry : avatarMap.entrySet()) {
            if (issues.contains(entry.getKey().get(0)) && goals.contains(entry.getKey().get(1))) {
                assignedAvatar = entry.getValue();
                break;
            }
        }

        user.setAvatar(assignedAvatar.getAvatarName());

        // Update the user in the database
       // userRepository.save(user);

        userRepository.save(user);
        return user.getAvatar();
      //  return null;
    }

    public User loginUser(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }

        return user;
    }

}
