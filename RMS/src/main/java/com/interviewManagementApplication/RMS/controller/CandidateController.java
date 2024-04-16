package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.model.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/candidate")
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

    @PostMapping("/add_candidate/{vacancyID}")
    public ResponseEntity<Void> addCandidate(@Validated @ModelAttribute Candidate candidate, @RequestParam("file") MultipartFile file, @PathVariable int vacancyID) {
        try {
            candidateService.addCandidate(candidate, file, vacancyID);
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


    //hire or reject candidate
    @PutMapping("/hire/{candidateid}")
    public String hire(@PathVariable int candidateid) {
        return candidateService.hireCandidate(candidateid);
    }

    @PutMapping("/reject/{candidateid}")
    public String reject(@PathVariable("candidateid") int candidateid) {
        return candidateService.rejectCandidate(candidateid);
    }
}
