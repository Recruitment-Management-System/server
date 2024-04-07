package com.interviewManagementApplication.RMS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {
    @GetMapping("/demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("Hello from demo");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminonly(){
        return ResponseEntity.ok("Hello from ADMIN");
    }
}
