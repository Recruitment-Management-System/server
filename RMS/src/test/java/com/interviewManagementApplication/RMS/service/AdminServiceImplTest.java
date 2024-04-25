package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.service.Impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEnableUser_Success() {

        User user = new User();
        user.setId(1);
        user.setActive(0);
        Optional<User> optionalUser = Optional.of(user);


        when(userRepository.findById(1)).thenReturn(optionalUser);


        adminService.enableUser(1);
        assertEquals(1, user.getActive());
        verify(userRepository).save(user);
    }

    @Test
    public void testDisableUser_Success() {
        User user = new User();
        user.setId(1);
        user.setActive(1);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(1)).thenReturn(optionalUser);

        adminService.disableUser(1);
        assertEquals(0, user.getActive());
        verify(userRepository).save(user);
    }

    @Test
    public void testUserList_Success() {
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> result = adminService.userList();
        assertEquals(expectedUsers, result);
    }

    @Test
    public void testEnableUser_Exception() {
        when(userRepository.findById(anyInt())).thenThrow(new RuntimeException("Error"));

        adminService.enableUser(1);
    }

    @Test
    public void testDisableUser_Exception() {
        when(userRepository.findById(anyInt())).thenThrow(new RuntimeException("Error"));

        adminService.disableUser(1);
    }

    @Test
    public void testUserList_Exception() {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Error"));

        List<User> result = adminService.userList();
        assertEquals(null, result);
    }
}
