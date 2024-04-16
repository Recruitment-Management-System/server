package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.service.Interface.FeedbackHRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/feedbackhr")
public class FeedbackHRCtrl {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackCtrl.class);
    @Autowired
    private FeedbackHRService feedbackHRService;

    //feedback HR
    @PostMapping("/{interviewID}/savefeedbackhr")
    public FeedbackHR saveFeedBackHR(@PathVariable int interviewID, @RequestBody FeedbackHR feedbackhr){
        try{
            return feedbackHRService.saveFeedbackHR(interviewID, feedbackhr);
        }catch(Exception e){
            logger.error("error - savefeedback");
            throw e;
        }
    }

//    @GetMapping("/{feedbackhrid}")
//    public Optional<FeedbackHR> readFeedbackhrById(@PathVariable("feedbackhrid") int feedbackhrid) {
//        try {
//            return feedbackHRService.readFeedbackHRById(feedbackhrid);
//        } catch (Exception e) {
//            logger.error("error - readfeedbackById");
//            throw e;
//        }
//    }

    @GetMapping("/interview/{interviewid}")
    public ResponseEntity<FeedbackHR> getFeedbackByInterviewId(@PathVariable int interviewid) {
        FeedbackHR feedback = feedbackHRService.findFeedbackhrIdByInterviewId(interviewid);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/updatehr")
//    public FeedbackHR updateFeedbackhr(@RequestBody FeedbackHR feedbackhr) {
//        try {
//            return feedbackHRService.updateFeedbackHR(feedbackhr);
//        }catch(Exception e){
//            logger.error("error - updateFeedback");
//            throw e;
//        }
//    }

//    @PutMapping("/updatehr/{feedbackidhr}")
//    public FeedbackHR updateFeedbackhr(@PathVariable int feedbackidhr, @RequestBody FeedbackHR feedbackhr) {
//        try{
//            return feedbackHRService.updateFeedbackHR(feedbackidhr, feedbackhr);
//        }catch(Exception e){
//            logger.error("error - updateFeedback");
//            throw e;
//        }
//    }
}
