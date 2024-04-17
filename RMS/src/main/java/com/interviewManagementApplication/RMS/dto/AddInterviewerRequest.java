package com.interviewManagementApplication.RMS.dto;

import com.interviewManagementApplication.RMS.model.Interview;

import java.util.List;

public class AddInterviewerRequest {
    private List<Integer> userIDs;
    private Interview interview;

    public List<Integer> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Integer> userIDs) {
        this.userIDs = userIDs;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }
}
