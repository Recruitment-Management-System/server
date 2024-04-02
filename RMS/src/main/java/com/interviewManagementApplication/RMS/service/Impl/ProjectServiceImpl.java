package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class.getName());

    @Autowired
    private ProjectRepository projectRepository;

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
}
