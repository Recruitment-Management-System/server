package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.model.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/all_candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        try {
            List<Candidate> candidates = candidateService.getAllCandidates();
            return ResponseEntity.ok(candidates);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all candidates", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candidate>> getCandidate(@PathVariable Integer id) {
        try {
            Optional<Candidate> candidate = candidateService.getCandidate(id);
            return ResponseEntity.ok(candidate);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving candidate with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add_candidate")
    public ResponseEntity<Void> addCandidate(@RequestBody Candidate candidate) {
        try {
            candidateService.addCandidate(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error occurred while adding candidate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCandidate(@PathVariable Integer id, @RequestBody Candidate candidate) {
        try {
            candidateService.updateCandidate(id, candidate);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error occurred while updating candidate with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
