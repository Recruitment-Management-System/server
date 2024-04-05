package com.interviewManagementApplication.RMS.service.Impl;

import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.repository.VacancyRepository;
import com.interviewManagementApplication.RMS.service.Interface.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class VacancyServiceImpl implements VacancyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VacancyServiceImpl.class.getName());

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Vacancy createVacancy(int projectID, Vacancy vacancy) {
       try{
           Optional<Project> optionalProject = projectRepository.findById(projectID);

           if (optionalProject.isPresent()) {
               Project project = optionalProject.get();
               vacancy.getJobRefCode();
               vacancy.setProject(project);
               //vacancy.setStatus(VacancyStatusType.CLOSED);
               return vacancyRepository.save(vacancy);
           } else {
               throw new IllegalArgumentException("Project not found with id: " + projectID);
           }
       }catch(Exception e){
           LOGGER.error("Cannot save vacancy");
           throw e;
       }
    }

    @Override
    public void deleteVacancy(Integer vacancyID) {
        try{
            if (vacancyRepository.existsById(vacancyID)) {
                vacancyRepository.deleteById(vacancyID);
            } else {
                throw new NoSuchElementException("Vacancy not found with id: " + vacancyID);
            }
        }catch(Exception e){
            LOGGER.error("Cannot delete vacancy");
            throw e;
        }
    }

    @Override
    public List<Vacancy> findAll() {
        try{
            return vacancyRepository.findAll();
        }catch(Exception e){
            LOGGER.error("Cannot fetch all vacancies");
            throw e;
        }
    }

    @Override
    public Optional<Vacancy> findByIdVacancy(Integer vacancyID) {
        try{
            if (vacancyID != null) { // Check if vacancyID is not null
                return vacancyRepository.findById(vacancyID);
            } else {
                throw new IllegalArgumentException("Vacancy ID cannot be null");
            }
        }catch(Exception e){
            LOGGER.error("Cannot find vacancy");
            throw e;
        }
    }

    @Override
    public Vacancy updateVacancyById(Integer vacancyID, Vacancy vacancy) {
        try{
            Vacancy existingVacancy = vacancyRepository.findById(vacancyID)
                    .orElseThrow(() -> new RuntimeException("Vacancy not found with id: " + vacancyID));

            existingVacancy.setReason(vacancy.getReason());
            existingVacancy.setJobRefCode(vacancy.getJobRefCode());
            existingVacancy.setJobRole(vacancy.getJobRole());
            existingVacancy.setOpenings(vacancy.getOpenings());
            existingVacancy.setStatus(vacancy.getStatus());

            return vacancyRepository.save(existingVacancy);
        }catch(Exception e){
            LOGGER.error("Cannot update the vacancy");
            throw e;
        }
    }

    public List<Vacancy> getVacanciesByProjectId(Integer projectId) {
        return vacancyRepository.findByProjectProjectID(projectId);
    }
}
