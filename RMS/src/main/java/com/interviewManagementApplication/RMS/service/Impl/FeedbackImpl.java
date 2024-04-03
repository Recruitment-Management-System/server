package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.*;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.FeedbackService;
import com.interviewManagementApplication.RMS.repository.FeedbackRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackImpl implements FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private InterviewRepo interviewRepo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        try{
            feedbackRepo.save(feedback);
        }catch(Exception e){
            logger.error("error - readAllFeedbacks");
            throw e;
        }
        return feedback;
    }

//    @Override
//    public Feedback saveFeedback(int interviewid, int id, Feedback feedback) {
//        try{
//            Optional<Interview> existingInterview = interviewRepo.findById(interviewid);
//            Optional<User> existingInterviewer = userRepository.findById(id);
//
//            if (existingInterview.isPresent() && existingInterviewer.isPresent()) {
//                Interview interview = existingInterview.get();
//                User interviewer = existingInterviewer.get();
//                feedback.getFeedbackDate();
//                feedback.getDetails();
//                feedback.getOverallRating();
//                feedback.setInterview(interview);
//                feedback.setUser(interviewer);
//                //vacancy.setStatus(VacancyStatusType.CLOSED);
//                return feedbackRepo.save(feedback);
//            } else {
//                throw new IllegalArgumentException("User or interview not exist with requested ID s");
//            }
//        }catch(Exception e){
//            logger.error("error - readAllFeedbacks");
//            throw e;
//        }
//    }


    @Override
    public List<Feedback> readAllFeedbacks() {
        try{
            return feedbackRepo.findAll();
        }catch(Exception e){
            logger.error("error - readAllFeedback");
            throw e;
        }
    }

    @Override
    public Optional<Feedback> readById(int id) {
        try{
            return feedbackRepo.findById(id);
        }catch (Exception e){
            logger.error("error - readbyid");
            throw e;
        }
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        try{
            return feedbackRepo.save(feedback);
        }catch (Exception e){
            logger.error("error - updateFeedback");
            throw e;
        }
    }

    @Override
    public void deleteFeedback(int id) {
        try{
            feedbackRepo.deleteById(id);
        }catch(Exception e){
            logger.error("error - delete");
            throw e;
        }
    }
}
