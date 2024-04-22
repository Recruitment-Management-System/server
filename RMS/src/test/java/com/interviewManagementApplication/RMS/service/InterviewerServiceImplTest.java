package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.InterviewStatus;
import com.interviewManagementApplication.RMS.model.Role;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.InterviewInterviewerRepo;
import com.interviewManagementApplication.RMS.repository.InterviewRepo;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Impl.interviewerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InterviewerServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private InterviewInterviewerRepo interviewInterviewerRepo;

    @Mock
    private InterviewRepo interviewRepo;

    @InjectMocks
    private interviewerServiceImpl interviewerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllInterviewers() {
        // Mock data
        User interviewer1 = new User();
        interviewer1.setId(1);
        interviewer1.setRole(Role.INTERVIEWER);

        User interviewer2 = new User();
        interviewer2.setId(2);
        interviewer2.setRole(Role.INTERVIEWER);

        List<User> expectedInterviewers = Arrays.asList(interviewer1, interviewer2);

        // Mock behavior
        when(userRepository.findByRole(Role.INTERVIEWER)).thenReturn(expectedInterviewers);

        // Test
        List<User> result = interviewerService.getAllInterviewers();
        assertEquals(expectedInterviewers, result);
    }

    @Test
    public void testGetAllProjectManagers() {
        // Mock data
        User projectManager1 = new User();
        projectManager1.setId(1);
        projectManager1.setRole(Role.PROJECT_MANAGER);

        User projectManager2 = new User();
        projectManager2.setId(2);
        projectManager2.setRole(Role.PROJECT_MANAGER);

        List<User> expectedProjectManagers = Arrays.asList(projectManager1, projectManager2);

        // Mock behavior
        when(userRepository.findByRole(Role.PROJECT_MANAGER)).thenReturn(expectedProjectManagers);

        // Test
        List<User> result = interviewerService.getAllProjectManagers();
        assertEquals(expectedProjectManagers, result);
    }

    @Test
    public void testGetProjectManager() {
        // Mock data
        User projectManager = new User();
        projectManager.setId(1);
        projectManager.setRole(Role.PROJECT_MANAGER);
        Optional<User> expectedProjectManager = Optional.of(projectManager);

        // Mock behavior
        when(userRepository.findById(1)).thenReturn(expectedProjectManager);

        // Test
        Optional<User> result = interviewerService.getProjectManger(1);
        assertEquals(expectedProjectManager, result);
    }

    @Test
    public void testUpdateInterviewStatus() {
        // Mock data
        int interviewId = 1;

        // Mock behavior
        when(interviewInterviewerRepo.countInterviewIdOfInterviewInterviewer(interviewId)).thenReturn(2);
        when(interviewRepo.countFeedbacks(interviewId)).thenReturn(2);

        // Test
        interviewerService.updateInterviewStatus(interviewId);
        verify(interviewRepo).updateInterviewStatus(interviewId, InterviewStatus.ENDED);
    }
}
