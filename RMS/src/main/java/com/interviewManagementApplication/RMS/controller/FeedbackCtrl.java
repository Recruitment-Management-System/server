package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.Service.Interface.FeedbackService;
import com.interviewManagementApplication.RMS.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackCtrl {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackCtrl.class);

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/savefeedback")
    public Feedback saveFeedBack(@RequestBody Feedback feedback){
        try{
            return feedbackService.saveFeedback(feedback);
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

    @GetMapping("/{feedbackid}")
    public Optional<Feedback> readFeedbackById(@PathVariable("feedbackid") int feedbackid) {
        try {
            return feedbackService.readById(feedbackid);
        } catch (Exception e) {
            logger.error("error - readfeedbackById");
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
}
