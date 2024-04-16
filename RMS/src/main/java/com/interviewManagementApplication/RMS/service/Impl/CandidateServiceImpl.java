package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.VacancyRepository;
import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    @Autowired
    private final CandidateRepo candidateRepo;

    @Autowired
    private final VacancyRepository vacancyRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepo candidateRepo, VacancyRepository vacancyRepository) {
        this.candidateRepo = candidateRepo;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Optional<Candidate> getCandidate(Integer id) {
        try {
            return candidateRepo.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while getting candidate with id: {}", id, e);
            return Optional.empty();
        }
    }


    @Override
    public void updateCandidate(Integer id, Candidate candidate) {
        try {
            Optional<Candidate> existingCandidate = candidateRepo.findById(id);
            if (existingCandidate.isPresent()) {
                Candidate updatedCandidate = existingCandidate.get();

                updatedCandidate.setFirstname(candidate.getFirstname());
                updatedCandidate.setLastname(candidate.getLastname());
                updatedCandidate.setNic(candidate.getNic());
                updatedCandidate.setExperience(candidate.getExperience());
                updatedCandidate.setQualification(candidate.getQualification());
                updatedCandidate.setDescription(candidate.getDescription());

                candidateRepo.save(updatedCandidate);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating candidate with id: {}", id, e);
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        try {
            return candidateRepo.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all candidates", e);
            return null;
        }
    }

    @Override
    public Candidate addCandidate(Candidate candidate, MultipartFile file, Integer vacancyID) throws IOException {
        try {
            Optional<Vacancy> vacancyOptional = vacancyRepository.findById(vacancyID);
            if (vacancyOptional.isPresent()) {
                Vacancy vacancy = vacancyOptional.get();
                if (vacancy.getCandidateList() == null) {
                    vacancy.setCandidateList(new ArrayList<>());
                }
                List<Candidate> candidateList = vacancy.getCandidateList();

                // Save file to disk
                String filePath = "/home/kmedagoda/Desktop/" + file.getOriginalFilename(); // Modify path as needed
                byte[] bytes = file.getBytes();
                Path path = Paths.get(filePath);
                Files.write(path, bytes);

//            candidate.setNic(candidate.getNic());
//            candidate.setFirstname(candidate.getFirstname());
//            candidate.setLastname(candidate.getLastname());
//            candidate.setDescription(candidate.getDescription());
//            candidate.setExperience(candidate.getExperience());
//            candidate.setQualification(candidate.getQualification());
                candidate.setCv(filePath);
                candidateList.add(candidate);
                vacancyRepository.save(vacancy);
           }

            // Save candidate to database
            return candidateRepo.save(candidate);

        } catch (Exception e) {
            logger.error("Error occurred while adding candidate", e);
            throw e;
        }
    }

    //update candidate status
    //hire
    @Override
    public String hireCandidate(int candidateid) {

        Optional<Candidate> existingCandidate = candidateRepo.findById(candidateid);
        if (existingCandidate.isPresent()) {
            Candidate updatedCandidate = existingCandidate.get();
            updatedCandidate.setStatus("Hired");
            candidateRepo.save(updatedCandidate);
        }
        return null;
    }

    @Override
    public String rejectCandidate(int candidateid) {

        Optional<Candidate> existingCandidate = candidateRepo.findById(candidateid);
        if (existingCandidate.isPresent()) {
            Candidate updatedCandidate = existingCandidate.get();
            updatedCandidate.setStatus("Rejected");
            candidateRepo.save(updatedCandidate);
        }
        return null;
    }
}

