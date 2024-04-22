package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.*;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import com.interviewManagementApplication.RMS.repository.InterviewInterviewerRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.util.JwtService;
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
    String secretKey = Consts.SecretKey;

    @Autowired
    private InterviewRepo interviewRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private InterviewInterviewerRepo interviewInterviewerRepo;

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
    public Optional<Interview> showInterview(Integer interviewid) {
        try {
            return interviewRepo.findById(interviewid);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving interview with id: {}", interviewid, e);
            return Optional.empty();
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
    public void addInterviewer(Integer candidateID, List<Integer> userIDs, Interview interview) {
        try{
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
        }}catch (Exception exception){
            logger.error("Error occurred while adding interview ",exception);

        }
    }

    @Override
    public void updateInterview(Integer candidateID,Integer interviewid,List<Integer> userIDs, Interview interview) {
        try {
            Optional<Candidate> candidateOptional = candidateRepo.findById(candidateID);
            if (candidateOptional.isPresent()) {
                Candidate savedCandidate = candidateOptional.get();
                Optional<Interview> existingInterviewOptional = interviewRepo.findById(interviewid);
                if (existingInterviewOptional.isPresent()) {
                    Interview existingInterview = existingInterviewOptional.get();
                    // Update existing interview details
                    existingInterview.setInterviewType(interview.getInterviewType());
                    existingInterview.setInterviewDate(interview.getInterviewDate());
                    existingInterview.setInterviewTime(interview.getInterviewTime());

                    // Clear existing user list
                    existingInterview.getUserList().clear();

                    // Add new users
                    List<User> userList = existingInterview.getUserList();
                    for (Integer userID : userIDs) {
                        Optional<User> userOptional = userRepository.findById(userID);
                        userOptional.ifPresent(userList::add);
                    }

                    // Set candidate for interview
                    existingInterview.setCandidate(savedCandidate);

                    // Save updated interview
                    interviewRepo.save(existingInterview);
                } else {
                    logger.error("Interview not found");
                }
            } else {
                logger.error("Candidate not found");
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating interview for candidate with ID: {}", candidateID, e);
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


                updatedInterview.setInterviewStatus(InterviewStatus.ENDED);

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

    @Override
    public List getAllInterviewersForAnInterview(int interviewid) throws Exception {
        return interviewInterviewerRepo.getInterviewerListForAnInterview(interviewid);
    }
}
