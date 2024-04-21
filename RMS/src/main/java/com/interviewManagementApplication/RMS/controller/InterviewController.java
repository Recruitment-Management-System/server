package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.dto.AddInterviewerRequest;
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

    //get all interviews
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

    //get the details of a given id
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



    @GetMapping("/interviews/{id}")
    public List<Interview> getInterviewsByUserId(@PathVariable Integer id) {
        return interviewService.getAllInterviewsByUserId(id);
    }

    @GetMapping("candidates/{candidateid}")
    public List<Interview> getCandidates(@PathVariable Integer candidateid) {
        return interviewService.getInterviewsByCandidate(candidateid);
    }

    //update interview status
    @PutMapping("/{interviewID}/updateStatus")
    public void updateInterviewStatus(@PathVariable int interviewID){
        try{
            interviewService.updateInterviewStatus(interviewID);
        }catch(Exception e){
            try {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //get candidate id of the interview
    @GetMapping("/{interviewid}/candidateId")
    public ResponseEntity<Integer> getCandidateIdByInterviewId(@PathVariable int interviewid) throws Exception {
        int candidateId = interviewService.getCandidateIdOfInterview(interviewid);
        return ResponseEntity.ok().body(candidateId);
    }

    //update interview status
    @GetMapping("/updateInterviewStatus/{interviewid}")
    public void updateInterviewStatus (@PathVariable Integer interviewid) throws Exception {
        interviewService.updateInterviewStatus(interviewid);
    }
}
