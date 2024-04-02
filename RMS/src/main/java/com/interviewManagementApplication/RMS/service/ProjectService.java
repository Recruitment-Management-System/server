package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAllProjects();
    Optional<Project> findProject(Integer projectID);
}
