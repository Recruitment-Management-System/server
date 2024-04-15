package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.dto.AddInterviewerRequest;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    //get all interviews
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

    //get the details of a given id
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

    //add interview to system
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


    //update interview by it's id
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


    //add interviewers to interview
    @PostMapping("/interviewer/{candidateID}")
    public void addInterviewer(@PathVariable Integer candidateID, @RequestBody AddInterviewerRequest addInterviewerRequest){
        try {
            List<Integer> userIDs = addInterviewerRequest.getUserIDs();
            Interview interview = addInterviewerRequest.getInterview();
            interviewService.addInterviewer(candidateID,userIDs,interview);
        }catch (Exception e){
            logger.error("Error in adding interviewer" + e);
        }
    }
}
