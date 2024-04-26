package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.InterviewStatus;
import com.interviewManagementApplication.RMS.model.InterviewType;
import com.interviewManagementApplication.RMS.repository.FeedbackRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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
        Interview interview = new Interview(1, InterviewType.TECHNICAL, InterviewStatus.ENDED, null, null, null, null, null, null);
        Feedback expectedFeedback = new Feedback(1, null, 4, true, new Date(), interview, 1);

        // Mock - findById method
        when(feedbackRepo.findById(interview.getInterviewid())).thenReturn(Optional.of(expectedFeedback));

        Optional<Feedback> feedback = feedbackimpl.readById(1);

        // Assertions
        assertEquals(true, feedback.isPresent());
        assertEquals(expectedFeedback, feedback.get());
    }

    @Test
    public void testFindFeedbackIdByInterviewId() {
        int interviewId = 1;
        List<Feedback> expectedFeedbacks = Arrays.asList(new Feedback(), new Feedback());
        when(feedbackRepo.findByInterview_Interviewid(interviewId)).thenReturn(expectedFeedbacks);

        List<Feedback> actualFeedbacks = feedbackimpl.findFeedbackIdByInterviewId(interviewId);

        verify(feedbackRepo).findByInterview_Interviewid(interviewId);

        assertEquals(expectedFeedbacks, actualFeedbacks);
    }

    @Test
    public void saveFeedbackTest(){
        Interview interview = new Interview(1, InterviewType.TECHNICAL, InterviewStatus.ENDED, null, null, null, null, null, null);

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