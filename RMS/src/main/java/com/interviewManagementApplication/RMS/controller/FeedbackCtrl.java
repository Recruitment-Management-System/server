package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.service.Interface.FeedbackService;
import com.interviewManagementApplication.RMS.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            return feedbackService.findAllFeedbacks();
        }catch(Exception e){
            logger.error("error - readallFeedbacks");
            throw e;
        }
    }

    @GetMapping("/{userid}")
    public List<Feedback> readAllFeedbacksForUser(Integer userid){
        try{
            return feedbackService.findByUserId(userid);
        }catch (Exception e){
            logger.error("error - find by user id");
            throw e;
        }
    }


//    @PutMapping("/update")
//    public Feedback updateFeedback(@RequestBody Feedback feedback) {
//        try {
//            return feedbackService.updateFeedback(feedback);
//        }catch(Exception e){
//            logger.error("error - updateFeedback");
//            throw e;
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public void deleteFeedback(@PathVariable("id") int id) {
//        try {
//            feedbackService.deleteFeedback(id);
//        }catch(Exception e){
//            logger.error("Delete not succeed");
//        }
//    }




    @GetMapping("/interview/{interviewid}")
    public List<Feedback> getFeedbackByInterviewId(@PathVariable int interviewid) {
        try{
            return feedbackService.findFeedbackIdByInterviewId(interviewid);
        }catch (Exception e){
            logger.error("error - find by user id");
            throw e;
        }
    }

    @GetMapping("/all/{feedbackid}")
    public Optional<Feedback> getFeedbackById(@PathVariable int feedbackid){
        try{
            return feedbackService.readById(feedbackid);
        }catch(Exception e){
            logger.error("error - find by id");
            throw e;
        }
    }

}
