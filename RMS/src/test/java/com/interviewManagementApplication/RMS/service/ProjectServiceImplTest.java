package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.service.Impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindProject(){

        Project project = new Project();
        project.setProjectID(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        Optional<Project> foundProject = projectService.findProject(1);
        assertTrue(foundProject.isPresent());
    }

    @Test
    public void testFIndProject_ProjectNotFound(){

        when(projectRepository.findById(anyInt())).thenThrow(new IllegalArgumentException("Cannot find the project"));
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class, ()-> projectService.findProject(1));

        assertEquals("Cannot find the project", illegalArgumentException.getMessage());
    }

    @Test
    public void testFindAllProjects(){
        Project project1 = new Project();
        project1.setProjectID(1);
        project1.setProjectName("Sample Project");
        project1.setProjectCode("PRJ001");

        Project project2 = new Project();
        project2.setProjectID(2);
        project2.setProjectName("Sample Project 2");
        project2.setProjectCode("PRJ002");

        List<Project> projectList = new ArrayList<>();
        projectList.addAll(Arrays.asList(project1, project2));

        when(projectRepository.findAll()).thenReturn(projectList);
        List<Project> projects = projectService.findAllProjects();

        assertEquals(projectList, projects);
    }
    @Test
    public void testFindAllProjects_ProjectsNotFound(){
        when(projectRepository.findAll()).thenThrow(new RuntimeException("Cannot find all projects"));
        RuntimeException exception = assertThrows(
                RuntimeException.class, ()-> projectService.findAllProjects());

        assertEquals("Cannot find all projects", exception.getMessage());
    }
}
