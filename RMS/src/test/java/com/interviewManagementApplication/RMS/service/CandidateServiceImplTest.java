package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.Feedback;
import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import com.interviewManagementApplication.RMS.repository.FeedbackRepo;
import com.interviewManagementApplication.RMS.repository.VacancyRepository;
import com.interviewManagementApplication.RMS.service.FileService;
import com.interviewManagementApplication.RMS.service.Impl.CandidateServiceImpl;
import com.interviewManagementApplication.RMS.service.Interface.VacancyService;
import com.interviewManagementApplication.RMS.util.FTPUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateServiceImplTest {

    @Mock
    private CandidateRepo candidateRepo;

    @Mock
    private FileService fileService;

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private VacancyService vacancyService;

    @Mock
    private FeedbackRepo feedbackRepo;

    @Mock
    private FTPUtils ftpUtils;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCandidate() {
        Candidate candidate = new Candidate();
        candidate.setCandidateID(1);
        candidate.setFirstname("John");
        Optional<Candidate> optionalCandidate = Optional.of(candidate);

        when(candidateRepo.findById(1)).thenReturn(optionalCandidate);

        Optional<Candidate> result = candidateService.getCandidate(1);
        assertEquals(optionalCandidate, result);
    }

    @Test
    public void testUpdateCandidate() {
        Candidate existingCandidate = new Candidate();
        existingCandidate.setCandidateID(1);
        existingCandidate.setFirstname("John");
        Optional<Candidate> optionalExistingCandidate = Optional.of(existingCandidate);

        Candidate updatedCandidate = new Candidate();
        updatedCandidate.setCandidateID(1);
        updatedCandidate.setFirstname("Jane");

        when(candidateRepo.findById(1)).thenReturn(optionalExistingCandidate);

        candidateService.updateCandidate(1, updatedCandidate);
        assertEquals("Jane", existingCandidate.getFirstname());
        verify(candidateRepo).save(existingCandidate);
    }



    @Test
    public void testHireCandidate() {
        Candidate candidate = new Candidate();
        candidate.setCandidateID(1);
        candidate.setStatus("Pending");
        Optional<Candidate> optionalCandidate = Optional.of(candidate);

        when(candidateRepo.findById(1)).thenReturn(optionalCandidate);

        String result = candidateService.hireCandidate(1);
        assertEquals("Hired", candidate.getStatus());
        verify(candidateRepo).save(candidate);
    }

    @Test
    public void testRejectCandidate() {
        Candidate candidate = new Candidate();
        candidate.setCandidateID(1);
        candidate.setStatus("Pending");
        Optional<Candidate> optionalCandidate = Optional.of(candidate);

        when(candidateRepo.findById(1)).thenReturn(optionalCandidate);

        String result = candidateService.rejectCandidate(1);
        assertEquals("Rejected", candidate.getStatus());
        verify(candidateRepo).save(candidate);
    }


}

