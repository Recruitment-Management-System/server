package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.service.Interface.InterviewService;
import com.interviewManagementApplication.RMS.model.Interview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private static final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private CandidateService candidateService;

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
    public Optional<?> getInterview(@PathVariable Integer id) {
        try {
            Optional<Interview> interviews= interviewService.showInterview(id);

            Integer candidateID = Integer.valueOf(interviews.get().getCandidate().getCandidateID());
            Optional<Candidate> candidates = candidateService.getCandidate(candidateID);
            return candidates;
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


    @PostMapping("/assign")
    public ResponseEntity<String> assignUserToInterview(@RequestParam Integer interviewId, @RequestParam Integer userId) {
        interviewService.assignUserToInterview(interviewId, userId);
        return ResponseEntity.ok("User assigned to interview successfully");
    }


    @GetMapping("/interviews/{id}")
    public List<Interview> getInterviewsByUserId(@PathVariable Integer id) {
        return interviewService.getAllInterviewsByUserId(id);
    }

    @GetMapping("candidates/{candidateid}")
    public List<Interview> getCandidates(@PathVariable Integer candidateid) {
        return interviewService.getCandidates(candidateid);
    }

    //update interview status
    @PutMapping("/{interviewID}/updateStatus")
    public void updateInterviewStatus(@PathVariable int interviewID){
        try{
            interviewService.updateInterviewStatus(interviewID);
        }catch(Exception e){
            throw e;
        }
    }
}
