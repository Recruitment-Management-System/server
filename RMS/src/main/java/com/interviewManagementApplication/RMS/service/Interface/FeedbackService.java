package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback saveFeedback(int interviewID, Feedback feedback);

    List<Feedback> readAllFeedbacks();

    Optional<Feedback> readById(int id);

    Feedback updateFeedback(Feedback feedback);

    void deleteFeedback(int id);


    //feedback HR
    FeedbackHR saveFeedbackHR(int interviewID, FeedbackHR feedbackhr);

    List<FeedbackHR> readAllFeedbacksHR();

    Optional<FeedbackHR> readFeedbackHRById(int id);

    FeedbackHR updateFeedbackHR(FeedbackHR feedbackhr);
}
