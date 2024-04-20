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
    public void updateInterviewStatus(Integer interviewid) {
        int countInterviewInterviewer = interviewInterviewerRepo.countInterviewIdOfInterviewInterviewer(interviewid);
        int countFeedbacks = interviewRepo.countFeedbacks(interviewid);

        if(countFeedbacks != 0 && countInterviewInterviewer != 0 && countInterviewInterviewer == countFeedbacks){
            interviewRepo.updateInterviewStatus(interviewid, InterviewStatus.ENDED);
        }
    }

}
