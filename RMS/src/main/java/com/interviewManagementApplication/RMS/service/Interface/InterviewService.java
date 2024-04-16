package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    List<Interview> getAllInterviews();

    void saveInterview(Interview interview);

    Optional<Interview> showInterview(Integer interviewID);

    void updateInterview(Integer id, Interview interview);


    void assignUserToInterview(Integer interviewId, Integer userId);
    List<Interview> getAllInterviewsByUserId(Integer userId);
    List<Interview> getCandidates(int candidateid);

    //update interview status
    void updateInterviewStatus(int interviewID);
}
