package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.User;

import java.util.List;
import java.util.Optional;

public interface InterviewerService {
    List<User> getAllInterviewers();
    List<User> getAllProjectManagers();

    Optional<User> getProjectManger(Integer userId);
}
