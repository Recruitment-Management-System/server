package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.Vacancy;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    List<Interview> getAllInterviews();

    void saveInterview(Interview interview);

    Optional<Interview> showInterview(Integer interviewID);

    void updateInterview(Integer id, Interview interview);


    List<Interview> getAllInterviewsByUserId(Integer userId);
    List<Interview> getInterviewsByCandidate(Integer candidateId);
}
