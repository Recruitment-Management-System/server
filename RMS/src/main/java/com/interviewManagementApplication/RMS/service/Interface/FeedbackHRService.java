package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;

import java.util.List;
import java.util.Optional;

public interface FeedbackHRService {

    FeedbackHR findFeedbackhrIdByInterviewId(int interviewid);

    //feedback HR
    FeedbackHR saveFeedbackHR(int interviewID, FeedbackHR feedbackhr);

    List<FeedbackHR> readAllFeedbacksHR();

    Optional<FeedbackHR> readFeedbackHRById(int id);

    FeedbackHR updateFeedbackHR(FeedbackHR feedbackhr);
}
