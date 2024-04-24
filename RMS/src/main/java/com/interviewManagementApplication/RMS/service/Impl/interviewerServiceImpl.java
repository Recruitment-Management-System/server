package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.InterviewStatus;
import com.interviewManagementApplication.RMS.model.Role;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.InterviewInterviewerRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class interviewerServiceImpl implements InterviewerService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InterviewInterviewerRepo interviewInterviewerRepo;

    @Autowired
    private InterviewRepo interviewRepo;

    @Override
    public List<User> getAllInterviewers() {
        return userRepository.findByRole(Role.INTERVIEWER);
    }

    @Override

    public List<User> getAllProjectManagers() {
        return userRepository.findByRole(Role.PROJECT_MANAGER);
    }

    @Override
    public Optional<User> getProjectManger(Integer userId) {
        return userRepository.findById(userId);
    }






}
