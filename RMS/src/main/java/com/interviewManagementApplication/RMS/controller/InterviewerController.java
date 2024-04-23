package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.service.Interface.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class InterviewerController {

    @Autowired
    private InterviewerService interviewerService;

    @GetMapping("/interviewers")
    public List<User> getAllInterviewers() {
        return interviewerService.getAllInterviewers();
    }

    @GetMapping("/projectManagers")
    public List<User> getAllProjectManagers() {
        return interviewerService.getAllProjectManagers();
    }

    @GetMapping("/projectManager/{userId}")
    public Optional<User> getProjectManager(@PathVariable Integer userId){
        return interviewerService.getProjectManger(userId);
    }
}
