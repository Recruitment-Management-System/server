package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.service.Interface.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class.getName());

    @Autowired
    private ProjectService projectService;

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
}
