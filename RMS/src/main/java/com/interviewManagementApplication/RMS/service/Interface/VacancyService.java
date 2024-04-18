package com.interviewManagementApplication.RMS.service.Interface;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    Vacancy createVacancy(int projectID, Vacancy vacancy);
    void deleteVacancy(Integer vacancyID);
    List<Vacancy> findAllVacancies();
    Optional<Vacancy> findByIdVacancy(Integer id);
    Vacancy updateVacancyById(Integer vacancyID, Vacancy vacancy);

    List<Vacancy> getVacanciesByProjectId(Integer projectId);


    //fetch candidate details using vacancy id
    List<Candidate> findCandidatesForVacancy(int vacancyid);
}

