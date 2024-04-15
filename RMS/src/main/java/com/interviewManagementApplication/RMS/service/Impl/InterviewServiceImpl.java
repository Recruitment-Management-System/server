package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private static final Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);

    @Autowired
    private InterviewRepo interviewRepo;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Interview> getAllInterviews() {
        try {
            return interviewRepo.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all interviews", e);
            return null;
        }
    }

    @Override
    public void saveInterview(Interview interview) {
        try {
            interviewRepo.save(interview);
        } catch (Exception e) {
            logger.error("Error occurred while saving interview", e);
        }
    }

    @Override
    public Optional<Interview> showInterview(Integer interviewID) {
        try {
            return interviewRepo.findById(interviewID);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving interview with id: {}", interviewID, e);
            return Optional.empty();
        }
    }

    @Override
    public void updateInterview(Integer id, Interview interview) {
        try {
            Optional<Interview> existingInterview = interviewRepo.findById(id);

            if (existingInterview.isPresent()) {
                Interview updatedInterview = existingInterview.get();

                updatedInterview.setInterviewTime(interview.getInterviewTime());
                updatedInterview.setInterviewDate(interview.getInterviewDate());
                updatedInterview.setInterviewStatus(interview.getInterviewStatus());
                updatedInterview.setCandidate(interview.getCandidate());
                updatedInterview.setInterviewType(interview.getInterviewType());

                interviewRepo.save(updatedInterview);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating interview with id: {}", id, e);
        }
    }

    @Override
    public void addInterviewer(Integer candidateID, List<Integer> userIDs, Interview interview) {
        Optional<Candidate> candidateOptional = candidateRepo.findById(candidateID);
        if (candidateOptional.isPresent()) {
            Candidate savedCandidate = candidateOptional.get();
            interview.setCandidate(savedCandidate);

            if (interview.getUserList() == null) {
                interview.setUserList(new ArrayList<>());
            }

            List<User> userList = interview.getUserList();

            for (Integer userID : userIDs) {
                Optional<User> userOptional = userRepository.findById(userID);
                userOptional.ifPresent(userList::add);
            }

            interviewRepo.save(interview);
        } else {
            logger.error("Candidate not found");
        }
    }

}
