package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.VacancyRepository;
import com.interviewManagementApplication.RMS.service.FileService;
import com.interviewManagementApplication.RMS.service.Interface.CandidateService;
import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.repository.CandidateRepo;
import com.interviewManagementApplication.RMS.service.Interface.VacancyService;
import com.interviewManagementApplication.RMS.util.FTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService, Consts {

    private static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    @Autowired
    private final CandidateRepo candidateRepo;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FTPUtils ftpUtils;


    @Autowired
    public CandidateServiceImpl(CandidateRepo candidateRepo, VacancyRepository vacancyRepository) {
        this.candidateRepo = candidateRepo;
        this.vacancyRepository = vacancyRepository;
    }

//    @Override
//    public Optional<Candidate> getCandidate(Integer id) {
//        try {
//            return candidateRepo.findById(id);
//        } catch (Exception e) {
//            logger.error("Error occurred while getting candidate with id: {}", id, e);
//            return Optional.empty();
//        }
//    }

    @Override
    public Optional<Candidate> getCandidate(Integer id) {
        try {
            Optional<Candidate> candidateOptional = candidateRepo.findById(id);
            candidateOptional.ifPresent(candidate -> {
                String cvPath = candidate.getCv();
                if (cvPath != null) {
                    // Fetch the CV file from the FTP server
                    byte[] fileContent = fileService.getCVFile(cvPath);
                    // Set the file content to the Candidate object
                    candidate.setCvpath(fileContent);
                }
            });
            return candidateOptional;
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
    public Candidate addCandidate(Candidate candidate, MultipartFile file, int vacancyID) throws IOException{
        try {
            String filePath = REMOTE_DIRECTORY + ftpUtils.generateUniqueFilename(file.getOriginalFilename());

            //upload file to ftp server
            fileService.uploadFile(file.getInputStream(), filePath);

            candidate.setCv(filePath);

            if (candidate.getVacancyList() == null) {
                candidate.setVacancyList(new ArrayList<>());
            }

                Optional<Vacancy> vacancyOptional = vacancyRepository.findById(vacancyID);
            if (vacancyOptional.isPresent()) {
                Vacancy vacancy = vacancyOptional.get();
                candidate.getVacancyList().add(vacancy); // Add the vacancy to the candidate's vacancy list
                vacancy.getCandidateList().add(candidate); // Add the candidate to the vacancy's candidate list

                // Save candidate and vacancy to update the relationship
                candidateRepo.save(candidate);
                vacancyRepository.save(vacancy);
            } else {
                throw new IllegalArgumentException("Vacancy with ID " + vacancyID + " not found.");
            }

            // Save candidate to database
            return candidateRepo.save(candidate);
        }

        catch (Exception e) {
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

