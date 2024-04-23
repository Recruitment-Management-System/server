package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.service.Interface.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PutMapping("/enable-user/{userID}")
    public void enableUser(@PathVariable Integer userID) {
        try{
            adminService.enableUser(userID);
        }catch (Exception error){
            logger.error("Error while enable user " + error);
        }
    }


    @PutMapping("/disable-user/{userID}")
    public void disableUser(@PathVariable Integer userID) {
        try{
            adminService.disableUser(userID);
        }catch (Exception error){
            logger.error("Error while enable user " + error);
        }
    }

    @GetMapping("/user-list")
    public List<User> userList(){
        return adminService.userList();
    }
}
