package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.User;

import java.util.List;

public interface AdminService {
    void enableUser(Integer userID);

    void disableUser(Integer userID);

    List<User> userList();
}
