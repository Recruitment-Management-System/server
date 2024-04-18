package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    List<Interview> getAllInterviews();

    void saveInterview(Interview interview);

    Optional<Interview> showInterview(Integer interviewID);

    void updateInterview(Integer id, Interview interview);


    List<Interview> getAllInterviewsByUserId(Integer userId);

    List<Interview> getCandidates(int candidateid);

    //update interview status
    void updateInterviewStatus(int interviewID) throws Exception;
    List<Interview> getInterviewsByCandidate(Integer candidateId);


    //get candidate id of the interview
    int getCandidateIdOfInterview(int interviewid) throws Exception;


}
