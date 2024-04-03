package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    private final CandidateRepo candidateRepo;

    @Autowired
    public CandidateServiceImpl(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    @Override
    public Optional<Candidate> getCandidate(Integer id) {
        try {
            return candidateRepo.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while getting candidate with id: {}", id, e);
            return Optional.empty();
        }
    }


    @Override
    public void updateCandidate(Integer id, Candidate candidate) {
        try {
            Optional<Candidate> existingCandidate = candidateRepo.findById(id);
            if (existingCandidate.isPresent()){
                Candidate updatedCandidate = existingCandidate.get();

                updatedCandidate.setFirstname(candidate.getFirstname());
                updatedCandidate.setLastname(candidate.getLastname());
                updatedCandidate.setNic(candidate.getNic());
                updatedCandidate.setExperience(candidate.getExperience());
                updatedCandidate.setQualification(candidate.getQualification());
                updatedCandidate.setDescription(candidate.getDescription());

                candidateRepo.save(updatedCandidate);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating candidate with id: {}", id, e);
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        try {
            return candidateRepo.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all candidates", e);
            return null;
        }
    }

    @Override
    public void addCandidate(Candidate candidate) {
        try {
            candidateRepo.save(candidate);
        } catch (Exception e) {
            logger.error("Error occurred while adding candidate", e);
        }
    }
}
