package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.User;

import java.util.List;

public interface InterviewerService {
    List<User> getAllInterviewers();

    //update interviewStatus
    void updateInterviewStatus(Integer interviewid);
}
