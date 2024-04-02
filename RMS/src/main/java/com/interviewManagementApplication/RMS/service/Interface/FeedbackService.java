package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback saveFeedback(Feedback feedback);

    List<Feedback> readAllFeedbacks();

    Optional<Feedback> readById(int id);

    Feedback updateFeedback(Feedback feedback);

    void deleteFeedback(int id);
}
