package com.interviewManagementApplication.RMS.controller;

import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.service.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VacancyController.class.getName());
    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/{projectID}/add")
    public Vacancy createVacancy(@PathVariable int projectID, @RequestBody Vacancy vacancy){
        try{
            return vacancyService.createVacancy(projectID,vacancy);
        }catch(Exception e){
            LOGGER.error("Cannot add the vacancy");
            throw e;
        }
    }

    @DeleteMapping("/delete/{vacancyID}")
    public void deleteVacancy(@PathVariable Integer vacancyID){
        try{
            vacancyService.deleteVacancy(vacancyID);
        }catch(Exception e){
            LOGGER.error("Cannot delete vacancy");
            throw e;
        }

    }
    @GetMapping
    public List<Vacancy> findAll(){
        try{
            return vacancyService.findAll();
        }catch(Exception e){
            LOGGER.error("Cannot fetch all vacancies");
            throw e;
        }
    }

    @GetMapping("/{vacancyID}")
    public Optional<Vacancy> findByVacancy(@PathVariable Integer vacancyID){
        try{
            return vacancyService.findByIdVacancy(vacancyID);
        }catch(Exception e){
            LOGGER.error("Cannot find the vacancy");
            throw e;
        }
    }

    @PutMapping("/update/{vacancyID}")
    public Vacancy updateByIdVacancy(Integer vacancyID, Vacancy vacancy) {
        try{
            return vacancyService.updateVacancyById(vacancyID, vacancy);
        }catch(Exception e){
            LOGGER.error("Cannot update the vacancy");
            throw e;
        }
    }
}