package com.interviewManagementApplication.RMS.Service.Impl;

import com.interviewManagementApplication.RMS.Service.Interface.FeedbackService;
import com.interviewManagementApplication.RMS.model.Feedback;
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
    public void updateFeedback(Feedback feedback) {
        try{
            feedbackRepo.save(feedback);
        }catch (Exception e){
            logger.error("error - updateFeedback");
            throw e;
        }
    }
}
