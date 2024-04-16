package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.repository.FeedbackRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FeedbackImplTest {

    @InjectMocks
    private FeedbackImpl feedbackimpl;

    @Mock
    private InterviewRepo interviewRepo;

    @Mock
    private FeedbackRepo feedbackRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void readByIdTest(){
        Interview interview = new Interview(1, 1, 1, null, null, null, null, null, null);
        Feedback expectedFeedback = new Feedback(1, null, 4, true, new Date(), interview);

        // Mock - findById method
        when(feedbackRepo.findFeedbackByInterviewId(interview.getInterviewid())).thenReturn(expectedFeedback);

        Optional<Feedback> feedback = Optional.ofNullable(feedbackimpl.findFeedbackIdByInterviewId(interview.getInterviewid()));

        // Assertions
        assertEquals(true, feedback.isPresent());
        assertEquals(expectedFeedback, feedback.get());
    }

    @Test
    public void saveFeedbackTest(){
        Interview interview = new Interview(1, 1, 1, null, null, null, null, null, null);

        Feedback feedback = new Feedback();
        feedback.setFeedbackid(1);
        feedback.setDetails(null);
        feedback.setOverallrating(4);
        feedback.setSecondinterview(true);
        feedback.setFeedbackdate(new Date());
        feedback.setInterview(interview);

        //Mock - find interview by id
        when(interviewRepo.findById(1)).thenReturn(Optional.of(interview));

        //Mock - save feedback
        when(feedbackRepo.save(feedback)).thenReturn(feedback);

        //Assertion
        assertEquals(feedback, feedbackimpl.saveFeedback(1,feedback));
    }

    @Test
    public void throwsExceptionTest(){
         when(interviewRepo.findById(3)).thenReturn(Optional.empty());

        Feedback feedback = new Feedback();
        feedback.setFeedbackid(1);
        feedback.setDetails(null);
        feedback.setOverallrating(4);
        feedback.setSecondinterview(true);
        feedback.setFeedbackdate(new Date());

        assertThrows(IllegalArgumentException.class, () -> {
            feedbackimpl.saveFeedback(1,feedback);
        });
    }
}