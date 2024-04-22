package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.dto.AddProjectManagerRequest;
import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.repository.UserRepository;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

    @Test
    void deleteProject_Success() {
        doNothing().when(projectRepository).deleteById(1);

        assertDoesNotThrow(() -> projectService.deleteProject(1));
    }



    @Test
    void updateProject_Success() {
        Project existingProject = new Project();
        existingProject.setProjectID(1);
        existingProject.setProjectName("Old Project Name");
        existingProject.setProjectCode("Old Project Code");

        AddProjectManagerRequest request = new AddProjectManagerRequest();
        Project updatedProject = new Project();
        updatedProject.setProjectName("New Project Name");
        updatedProject.setProjectCode("New Project Code");
        request.setProject(updatedProject);
        request.setUserID(1);

        User user = new User();
        user.setId(1);

        when(projectRepository.findById(1)).thenReturn(Optional.of(existingProject));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(projectRepository.save(any())).thenReturn(updatedProject);

        Project result = projectService.updateProject(1, request);

        assertNotNull(result);
        assertEquals("New Project Name", result.getProjectName());
        assertEquals("New Project Code", result.getProjectCode());
    }

    @Test
    void findAllProjectsWithUserName_Success() {
        List<Object[]> projectsWithUserNames = new ArrayList<>();
        projectsWithUserNames.add(new Object[]{"Project 1", "User 1"});
        projectsWithUserNames.add(new Object[]{"Project 2", "User 2"});

        when(projectRepository.findAllProjectsWithUserName()).thenReturn(projectsWithUserNames);

        List<Object[]> result = projectService.findAllProjectsWithUserName();

        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0)[0]);
        assertEquals("User 1", result.get(0)[1]);
        assertEquals("Project 2", result.get(1)[0]);
        assertEquals("User 2", result.get(1)[1]);
    }

    @Test
    void getProjectsByProjectManagerID_Success() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(projectRepository.findByUsersId(1)).thenReturn(projects);

        List<Project> result = projectService.getProjectsByProjectManagerID(1);

        assertEquals(2, result.size());
    }

    @Test
    void findAllProjects_Success() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.findAllProjects();

        assertEquals(2, result.size());
    }
}
