package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback saveFeedback(int interviewID, Feedback feedback);

    List<Feedback> findAllFeedbacks();

    List<Feedback> findByUserid(Integer userid);

    // Optional<Feedback> readById(int id);

   // Feedback updateFeedback(Feedback feedback);

   // void deleteFeedback(int id);






    /////
    Feedback findFeedbackIdByInterviewId(int interviewid);


}
