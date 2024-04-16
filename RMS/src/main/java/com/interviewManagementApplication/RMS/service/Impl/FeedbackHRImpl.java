package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.FeedbackHRRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.service.Interface.FeedbackHRService;
import com.interviewManagementApplication.RMS.service.Interface.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackHRImpl implements FeedbackHRService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    private FeedbackHRRepo feedbackHRRepo;

    @Autowired
    private InterviewRepo interviewRepo;

    @Override
    public FeedbackHR findFeedbackhrIdByInterviewId(int interviewid) {
        return feedbackHRRepo.findFeedbackhrByInterviewId(interviewid);
    }

    //feedback HR
    @Override
    public FeedbackHR saveFeedbackHR(int interviewID, FeedbackHR feedbackhr) {
        try{
            Optional<Interview> existingInterview = interviewRepo.findById(interviewID);
            //Optional<User> existingInterviewer = userRepository.findById(id);

            if (existingInterview.isPresent()) {
                Interview interview = existingInterview.get();
                // User interviewer = existingInterviewer.get();
                feedbackhr.getFeedbackdate();
                feedbackhr.getComment();
                feedbackhr.getSalaryexpectation();
                feedbackhr.setInterview(interview);
                // feedback.setUser(interviewer);
                return feedbackHRRepo.save(feedbackhr);
            } else {
                throw new IllegalArgumentException("Interview not exist with requested ID s");
            }
        }catch(Exception e){
            logger.error("error - readAllFeedbacks");
            throw e;
        }
    }

//    @Override
//    public List<FeedbackHR> readAllFeedbacksHR() {
//        try{
//            return feedbackHRRepo.findAll();
//        }catch(Exception e){
//            logger.error("error - readAllFeedback");
//            throw e;
//        }
//    }

//    @Override
//    public Optional<FeedbackHR> readFeedbackHRById(int id) {
//        try{
//            return feedbackHRRepo.findById(id);
//        }catch (Exception e){
//            logger.error("error - readbyid");
//            throw e;
//        }
//    }

//    @Override
//    public FeedbackHR updateFeedbackHR(FeedbackHR feedbackhr) {
//        try{
//            return feedbackHRRepo.save(feedbackhr);
//        }catch (Exception e){
//            logger.error("error - updateFeedback");
//            throw e;
//        }
//    }

//    @Override
//    public FeedbackHR updateFeedbackHR(int feedbackidhr, FeedbackHR feedbackHR) {
//        try{
//            FeedbackHR feedbackHR1 = feedbackHRRepo.findById(feedbackidhr)
//                    .orElseThrow(() -> new RuntimeException("Feedback not found"));
//
//            feedbackHR1.setFeedbackdate(feedbackHR.getFeedbackdate());
//            feedbackHR1.setSalaryexpectation(feedbackHR.getSalaryexpectation());
//            feedbackHR1.setComment(feedbackHR.getComment());
//
//            return feedbackHRRepo.save(feedbackHR1);
//        }catch(Exception e){
//            logger.error("error - update feedback");
//            throw e;
//        }
//    }
}
