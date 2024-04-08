package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
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
        return interviewRepo.findAllByUserList_Id(userId);
    }

    @Override
    public List<Interview> getCandidates(Integer candidateId) {
        return interviewRepo.findByCandidateCandidateID(candidateId);
    }

}
