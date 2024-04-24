package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.exceptions.UserNotActiveException;
import com.interviewManagementApplication.RMS.model.AuthenticationResponse;
import com.interviewManagementApplication.RMS.model.User;
import com.interviewManagementApplication.RMS.repository.UserRepository;
import com.interviewManagementApplication.RMS.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {
        try {
            User user = new User();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPosition(request.getPosition());
            user.setRole(request.getRole());
            user = userRepository.save(user);

            String token = jwtService.generateToken(user);

            return new AuthenticationResponse(token);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            throw new RuntimeException("Error occurred while registering user");
        }
    }

    public AuthenticationResponse authenticate(User request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getActive() == 0) {
                throw new UserNotActiveException("User is still not active");
            }

            String token = jwtService.generateToken(user);

            return new AuthenticationResponse(token);
        } catch (UserNotActiveException e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            throw new RuntimeException("Authentication failed");
        }
    }

}
