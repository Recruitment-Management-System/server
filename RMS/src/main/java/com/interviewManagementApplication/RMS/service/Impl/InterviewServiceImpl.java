package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.util.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private static final Logger logger = LoggerFactory.getLogger(InterviewServiceImpl.class);
    String secretKey = Consts.SecretKey;

    @Autowired
    private InterviewRepo interviewRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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
                updatedInterview.setInterviewdate(interview.getInterviewdate());
                updatedInterview.setInterviewStatus(interview.getInterviewStatus());
                updatedInterview.setCandidate(interview.getCandidate());
                updatedInterview.setInterviewType(interview.getInterviewType());

                interviewRepo.save(updatedInterview);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating interview with id: {}", id, e);
        }
    }



    public void assignUserToInterview(Integer interviewId, Integer userId) {
        Optional<Interview> optionalInterview = interviewRepo.findById(interviewId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalInterview.isPresent() && optionalUser.isPresent()) {
            Interview interview = optionalInterview.get();
            User user = optionalUser.get();

            interview.getUserList().add(user);
            user.getInterviewList().add(interview);

            interviewRepo.save(interview);
            userRepository.save(user);
        } else {
            // Handle invalid interviewId or userId
            throw new IllegalArgumentException("Invalid interviewId or userId");
        }
    }
    @Override
    public List<Interview> getAllInterviewsByUserId(Integer userId) {
        try{
            return interviewRepo.findAllByUserList_Id(userId);

        }catch(Exception e){
            logger.error("Error occurred while getting all interviews with id {}", userId);
            throw e;
        }
    }

    //interviews for candidate
    @Override

    public List<Interview> getCandidates(int candidateid) {
        return interviewRepo.findByCandidateCandidateID(candidateid);
    }



    //update interview status
    @Override
    public void updateInterviewStatus(int interviewID) {
        try {
            Optional<Interview> existingInterview = interviewRepo.findById(interviewID);

            if (existingInterview.isPresent()) {
                Interview updatedInterview = existingInterview.get();


                updatedInterview.setInterviewStatus(2);

                interviewRepo.save(updatedInterview);
            }
        } catch (Exception e) {
            logger.error("error - updating interview Status");
        }
    }

    @Override
    public List<Interview> getInterviewsByCandidate(Integer candidateId) {
        try{
            return interviewRepo.findByCandidateCandidateID(candidateId);
        }catch(Exception e){
            logger.error("Error occurred while getting all candidates with id {}", candidateId);
            throw e;
        }
    }



        @Override
    //get candidate id of the interview
    public int getCandidateIdOfInterview(int interviewid) throws Exception {
        Optional<Interview> interviewOptional = interviewRepo.findById(interviewid);

            if (interviewOptional.isPresent()) {
                Interview interview = interviewOptional.get();
                return interview.getCandidate().getCandidateID();
            }
            throw new Exception("Interview with id " + interviewid + " not found");
    }
}
