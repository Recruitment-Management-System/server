package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/all_interviews")
    public List<Interview> getAllInterviews() {
        try {
            return interviewService.getAllInterviews();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all interviews", e);
            // You can handle the exception or rethrow it if needed
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Optional<Interview> getInterview(@PathVariable Integer id) {
        try {
            return interviewService.showInterview(id);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving interview with id: {}", id, e);
            // You can handle the exception or rethrow it if needed
            throw e;
        }
    }

    @PostMapping("/add_interview")
    public void addInterview(@RequestBody Interview interview) {
        try {
            interviewService.saveInterview(interview);
        } catch (Exception e) {
            logger.error("Error occurred while adding interview", e);
            // You can handle the exception or rethrow it if needed
            throw e;
        }
    }

    @PutMapping("/{id}")
    public void changeInterview(@PathVariable Integer id, @RequestBody Interview interview) {
        try {
            interviewService.updateInterview(id, interview);
        } catch (Exception e) {
            logger.error("Error occurred while updating interview with id: {}", id, e);
            // You can handle the exception or rethrow it if needed
            throw e;
        }
    }
}
