package com.interviewManagementApplication.RMS.dto;

import com.interviewManagementApplication.RMS.model.Interview;
import com.interviewManagementApplication.RMS.model.Project;

import java.util.List;

public class AddProjectManagerRequest {
    private Integer  userID;
    private Project project;

    public int getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
