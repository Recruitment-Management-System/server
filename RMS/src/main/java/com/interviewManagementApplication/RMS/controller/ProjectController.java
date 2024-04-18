package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.dto.AddProjectManagerRequest;
import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class.getName());

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Project> findAllProjects(){
        try{
            return projectService.findAllProjects();
        }catch(Exception e){
            LOGGER.error("Cannot find projects");
            throw  e;
        }
    }

    @GetMapping("/{projectID}")
    public Optional<Project> findProject(@PathVariable Integer projectID){
        try{
            return projectService.findProject(projectID);

        }catch (Exception e){
            LOGGER.error("Cannot find the that project");
            throw e;
        }
    }

    @GetMapping("/project/{userId}")
    public List<Project> getProjectsByProjectManagerID(@PathVariable Integer userId){
        return projectService.getProjectsByProjectManagerID(userId);
    }

    @PostMapping("/project/save")
    public Project createProject(@RequestBody AddProjectManagerRequest addProjectManagerRequest) {
        Integer userID = addProjectManagerRequest.getUserID();
        Project project = addProjectManagerRequest.getProject();

        // Check if userID is provided
        if (userID == null) {
            throw new IllegalArgumentException("UserID cannot be null.");
        }

        Optional<User> user = userRepository.findById(userID);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userID + " not found.");
        }

        User existingUser = user.get();
        project.setUsers(existingUser);

        return projectService.createProject(existingUser, project);
    }
    @GetMapping("/projectsWithUserName")
    public List<Object[]> getProjectsWithUserName() {
        return projectService.findAllProjectsWithUserName();
    }
    @PutMapping("/update/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer projectId, @RequestBody AddProjectManagerRequest addProjectManagerRequest) {
        Project updatedProject = projectService.updateProject(projectId, addProjectManagerRequest);
        return ResponseEntity.ok(updatedProject);
    }
}
