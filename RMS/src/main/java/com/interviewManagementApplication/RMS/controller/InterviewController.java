package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.Service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/all_interviews")
    public ResponseEntity<List<Interview>> getAllInterviews() {
        try {
            List<Interview> interviews = interviewService.getAllInterviews();
            return ResponseEntity.ok(interviews);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all interviews", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterview(@PathVariable Integer id) {
        try {
            Interview interview = interviewService.showInterview(id);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving interview with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add_interview")
    public ResponseEntity<Void> addInterview(@RequestBody Interview interview) {
        try {
            interviewService.saveInterview(interview);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error occurred while adding interview", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> changeInterview(@PathVariable Integer id, @RequestBody Interview interview) {
        try {
            interviewService.updateInterview(id, interview);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error occurred while updating interview with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
