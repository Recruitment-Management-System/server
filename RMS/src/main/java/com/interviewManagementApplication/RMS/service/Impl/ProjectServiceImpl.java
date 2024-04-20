package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.dto.AddProjectManagerRequest;
import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class.getName());

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Project> findAllProjects() {
        try{
            return projectRepository.findAll();
        }catch(Exception e){
            LOGGER.error("Cannot find all projects");
            throw e;
        }
    }

    @Override
    public Optional<Project> findProject(Integer projectID) {
        try{
            return projectRepository.findById(projectID);
        }catch(Exception e){
            LOGGER.error("Cannot find the project" + projectID);
            throw e;
        }
    }

    @Override
    public List<Project> getProjectsByProjectManagerID(Integer id){
        try{
            return projectRepository.findByUsersId(id);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public Project createProject(User user, Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Object[]> findAllProjectsWithUserName() {
        return projectRepository.findAllProjectsWithUserName();
    }

    @Override
    public Project updateProject(Integer projectId, AddProjectManagerRequest request) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();

            // Update project details
            Project project = request.getProject();
            existingProject.setProjectName(project.getProjectName());
            existingProject.setProjectCode(project.getProjectCode());

            // Update user ID
            Integer userId = request.getUserID();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                existingProject.setUsers(user);
            } else {
                throw new IllegalArgumentException("User with ID " + userId + " not found.");
            }

            // Save the updated project
            return projectRepository.save(existingProject);
        } else {
            // Handle case where project with given ID does not exist
            throw new EntityNotFoundException("Project not found with ID: " + projectId);
        }
    }

}
