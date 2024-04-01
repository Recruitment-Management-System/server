package com.interviewManagementApplication.RMS.Service.Interface;

import com.interviewManagementApplication.RMS.model.Interview;

import java.util.List;
import java.util.Optional;

public interface InterviewService {

    List<Interview> getAllInterviews();

    void saveInterview(Interview interview);

    Optional<Interview> showInterview(Integer interviewID);

    void updateInterview(Integer id, Interview interview);
}
