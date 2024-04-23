package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.controller.CandidateController;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Interface.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void enableUser(Integer userID) {
        try{
            Optional<User> optionalUser = userRepository.findById(userID);
            if(optionalUser.isPresent()){
                User existUser = optionalUser.get();
                existUser.setActive(1);
                userRepository.save(existUser);
            }
        }catch (Exception error){
            logger.error("error while enabling user " + error);
        }
    }

    @Override
    public void disableUser(Integer userID) {
        try{
            Optional<User> optionalUser = userRepository.findById(userID);
            if(optionalUser.isPresent()){
                User existUser = optionalUser.get();
                existUser.setActive(0);
                userRepository.save(existUser);
            }
        }catch (Exception error){
            logger.error("error while disabling user " + error);
        }
    }

    @Override
    public List<User> userList() {
        try{
            return userRepository.findAll();
        }catch (Exception error){
            logger.error("error while fetching users " + error);
            return null;
        }
    }
}
