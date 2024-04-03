package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();

    void addCandidate(Candidate candidate);

    Optional<Candidate> getCandidate(Integer id);

    void updateCandidate(Integer id, Candidate candidate);
}
