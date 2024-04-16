package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.*;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.util.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        }}

        @Override
    public List<Interview> getInterviewsByCandidate(Integer candidateId) {
       try{
           return interviewRepo.findByCandidateCandidateID(candidateId);
       }catch(Exception e){
           logger.error("Error occurred while getting all candidates with id {}", candidateId);
           throw e;
       }
    }

}
