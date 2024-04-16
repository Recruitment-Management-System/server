package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();

    Candidate addCandidate(Candidate candidate, MultipartFile file, Integer vacancyID) throws IOException;

    Optional<Candidate> getCandidate(Integer id);

    void updateCandidate(Integer id, Candidate candidate);



    //hire or reject candidate
    String hireCandidate(int candidateid);
    String rejectCandidate(int candidateid);
}
