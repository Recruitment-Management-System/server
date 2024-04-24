package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback saveFeedback(int interviewID, Feedback feedback);

    List<Feedback> findAllFeedbacks();

    List<Feedback> findByUserId(Integer userId);

     Optional<Feedback> readById(int id);

   // Feedback updateFeedback(Feedback feedback);

   // void deleteFeedback(int id);






    /////
    List<Feedback> findFeedbackIdByInterviewId(int interviewid);


}
