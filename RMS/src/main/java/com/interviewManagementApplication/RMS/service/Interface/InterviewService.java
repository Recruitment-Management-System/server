package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    List<Interview> getAllInterviews();

    void saveInterview(Interview interview);

    Optional<Interview> showInterview(Integer interviewID);

    void updateInterview(Integer candidateID,Integer interviewid,List<Integer> userIDs, Interview interview);

    void addInterviewer(Integer candidateID, List<Integer> userIDs, Interview interview);


    List<Interview> getAllInterviewsByUserId(Integer userId);

    //interviews for candidate
    List<Interview> getCandidates(int candidateid);


    List<Interview> getInterviewsByCandidate(Integer candidateId);


    //get candidate id of the interview
    int getCandidateIdOfInterview(int interviewid) throws Exception;

    List<Integer> getAllInterviewersForAnInterview(int interviewid) throws Exception;

    //update interview status
    void updateInterviewStatus(Integer interviewid);
}
