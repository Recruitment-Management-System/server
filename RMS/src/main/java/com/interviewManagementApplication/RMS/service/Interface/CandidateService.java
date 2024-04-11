package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();

    void addCandidate(Candidate candidate);

    Optional<Candidate> getCandidate(Integer id);

    void updateCandidate(Integer id, Candidate candidate);



    //hire or reject candidate
    String hireCandidate(int candidateid);
    String rejectCandidate(int candidateid);
}
