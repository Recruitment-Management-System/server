package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.FeedbackHR;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.FeedbackHRRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FeedbackHRImplTest {

    @InjectMocks
    private FeedbackHRImpl feedbackHRimpl;

    @Mock
    private FeedbackHRRepo feedbackHRRepo;

    @Mock
    private InterviewRepo interviewRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void readFeedbackHRByIdTest(){
        Interview interview = new Interview(1, 1, 1, null, null, null, null, null, null);
        FeedbackHR expectedFeedback = new FeedbackHR(1, new Date(),120000, "comment", interview);

        // Mock - findById method
        when(feedbackHRRepo.findFeedbackhrByInterviewId(interview.getInterviewid())).thenReturn(expectedFeedback);

        Optional<FeedbackHR> feedbackhr = Optional.ofNullable(feedbackHRimpl.findFeedbackhrIdByInterviewId(interview.getInterviewid()));

        // Assertions
        assertEquals(true, feedbackhr.isPresent());
        assertEquals(expectedFeedback, feedbackhr.get());
    }

    @Test
    public void saveFeedbackHRTest(){
        Interview interview = new Interview(1, 1, 1, null, null, null, null, null, null);

        FeedbackHR feedbackhr = new FeedbackHR();
        feedbackhr.setFeedbackidhr(1);
        feedbackhr.setFeedbackdate(new Date());
        feedbackhr.setSalaryexpectation(120000);
        feedbackhr.setComment("comment");
        feedbackhr.setInterview(interview);

        //Mock - find interview by id
        when(interviewRepo.findById(1)).thenReturn(Optional.of(interview));

        //Mock - save feedback
        when(feedbackHRRepo.save(feedbackhr)).thenReturn(feedbackhr);

        //Assertion
        assertEquals(feedbackhr, feedbackHRimpl.saveFeedbackHR(1,feedbackhr));
    }


    @Test
    public void throwsExceptionHRTest(){
        when(interviewRepo.findById(3)).thenReturn(Optional.empty());

        FeedbackHR feedback = new FeedbackHR();
        feedback.setFeedbackidhr(1);
        feedback.setFeedbackdate(new Date());
        feedback.setSalaryexpectation(120000);
        feedback.setComment("comment");


        assertThrows(IllegalArgumentException.class, () -> {
            feedbackHRimpl.saveFeedbackHR(1,feedback);
        });
    }
}