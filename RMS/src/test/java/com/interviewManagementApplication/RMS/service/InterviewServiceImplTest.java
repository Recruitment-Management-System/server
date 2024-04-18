package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.*;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.service.Impl.InterviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class InterviewServiceImplTest {

    @Mock
    private InterviewRepo interviewRepo;

    @InjectMocks
    private InterviewServiceImpl interviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllInterviewsByUserId(){
        User user = new User();
        user.setId(1);

        Interview interview1 = new Interview();
        interview1.setInterviewID(1);
        interview1.setInterviewType(InterviewType.HR);
        interview1.setInterviewStatus(InterviewStatus.HAPPENING);

        Interview interview2 = new Interview();
        interview2.setInterviewID(2);
        interview2.setInterviewType(InterviewType.TECHNICAL);
        interview2.setInterviewStatus(InterviewStatus.ENDED);

        List<Interview> interviewList = new ArrayList<>();
        interviewList.addAll(Arrays.asList(interview1, interview2));

        when(interviewRepo.findAllByUserList_Id(1)).thenReturn(interviewList);
        List<Interview> interviews = interviewService.getAllInterviewsByUserId(1);

        assertEquals(interviewList, interviews);
    }

    @Test
    public void testGetAllInterviewsByUserId_NotFound(){

        when(interviewRepo.findAllByUserList_Id(anyInt())).thenThrow(new RuntimeException("Error occurred while getting all interviews"));
        RuntimeException exception = assertThrows(
                RuntimeException.class, ()-> interviewService.getAllInterviewsByUserId(1));

        assertEquals("Error occurred while getting all interviews", exception.getMessage());
    }

    @Test
    public void testGetAllInterviewsByCandidateId(){
        Candidate candidate = new Candidate();
        candidate.setCandidateID(1);

        Interview interview1 = new Interview();
        interview1.setInterviewID(1);
        interview1.setInterviewType(InterviewType.HR);
        interview1.setInterviewStatus(InterviewStatus.HAPPENING);

        Interview interview2 = new Interview();
        interview2.setInterviewID(2);
        interview2.setInterviewType(InterviewType.TECHNICAL);
        interview2.setInterviewStatus(InterviewStatus.ENDED);

        List<Interview> interviewList = new ArrayList<>();
        interviewList.addAll(Arrays.asList(interview1, interview2));

        when(interviewRepo.findByCandidateCandidateID(1)).thenReturn(interviewList);
        List<Interview> interviews = interviewService.getInterviewsByCandidate(1);

        assertEquals(interviewList, interviews);
    }
    @Test
    public void testGetAllInterviewsByCandidate_NotFound(){

        when(interviewRepo.findByCandidateCandidateID(anyInt())).thenThrow(new RuntimeException("Error occurred while getting all interviews"));
        RuntimeException exception = assertThrows(
                RuntimeException.class, ()-> interviewService.getInterviewsByCandidate(1));

        assertEquals("Error occurred while getting all interviews", exception.getMessage());
    }
}
