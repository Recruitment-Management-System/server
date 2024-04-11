package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.FeedbackHR;
import com.interviewManagementApplication.RMS.service.Interface.FeedbackService;
import com.interviewManagementApplication.RMS.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackCtrl {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackCtrl.class);

    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/{interviewID}/savefeedback")
    public Feedback saveFeedBack(@PathVariable int interviewID, @RequestBody Feedback feedback){
        try{
            return feedbackService.saveFeedback(interviewID,feedback);
        }catch(Exception e){
            logger.error("error - savefeedback");
            throw e;
        }
    }

    @GetMapping("/")
    public List<Feedback> readAllFeedbacks() {
        try {
            return feedbackService.readAllFeedbacks();
        }catch(Exception e){
            logger.error("error - readallFeedbacks");
            throw e;
        }
    }



    @PutMapping("/update")
    public Feedback updateFeedback(@RequestBody Feedback feedback) {
        try {
            return feedbackService.updateFeedback(feedback);
        }catch(Exception e){
            logger.error("error - updateFeedback");
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeedback(@PathVariable("id") int id) {
        try {
            feedbackService.deleteFeedback(id);
        }catch(Exception e){
            logger.error("Delete not succeed");
        }
    }




    @GetMapping("/interview/{interviewid}")
    public ResponseEntity<Feedback> getFeedbackByInterviewId(@PathVariable int interviewid) {
        Feedback feedback = feedbackService.findFeedbackIdByInterviewId(interviewid);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
