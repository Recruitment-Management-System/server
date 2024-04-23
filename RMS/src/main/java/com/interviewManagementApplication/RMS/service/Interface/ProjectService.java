package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.dto.AddProjectManagerRequest;
import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.User;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAllProjects();
    Optional<Project> findProject(Integer projectID);
    List<Project> getProjectsByProjectManagerID(Integer id);

    Project createProject(User user, Project project);

    List<Object[]> findAllProjectsWithUserName();

    Project updateProject(Integer projectId, AddProjectManagerRequest request);

    void deleteProject(Integer projectId);
}
