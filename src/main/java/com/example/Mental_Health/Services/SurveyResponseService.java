package com.example.Mental_Health.Services;

import com.example.Mental_Health.Models.SurveyResponse;
import com.example.Mental_Health.Repositories.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    public SurveyResponse saveSurveyResponse(SurveyResponse surveyResponse) {
        return surveyResponseRepository.save(surveyResponse);
    }
}