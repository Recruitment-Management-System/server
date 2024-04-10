package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.model.Candidate;
import com.interviewManagementApplication.RMS.model.Project;
import com.interviewManagementApplication.RMS.model.Vacancy;
import com.interviewManagementApplication.RMS.model.VacancyStatusType;
import com.interviewManagementApplication.RMS.repository.ProjectRepository;
import com.interviewManagementApplication.RMS.repository.VacancyRepository;
import com.interviewManagementApplication.RMS.service.Impl.VacancyServiceImpl;
import com.interviewManagementApplication.RMS.service.Interface.VacancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

public class VacancyServiceImplTest {

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private VacancyServiceImpl vacancyServiceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllVacancies(){
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("Sample Project");
        project.setProjectCode("PRJ001");


        Candidate candidate1 = new Candidate();
        candidate1.setCandidateID(101);
        candidate1.setFirstname("Hansaka");
        candidate1.setLastname("JS");

        Candidate candidate2 = new Candidate();
        candidate2.setCandidateID(102);
        candidate2.setFirstname("Kavinda");
        candidate2.setLastname("KP");

        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate1);
        candidateList.add(candidate2);

        List<Vacancy> vacancy = new ArrayList<>();

        vacancy.add(new Vacancy(1, "No-reason", "SE100", "Software Engineer", 2, VacancyStatusType.OPEN, project, candidateList));

        when(vacancyRepository.findAll()).thenReturn(vacancy);
        List<Vacancy> findVacancies = vacancyServiceImpl.findAllVacancies();

        assertEquals(vacancy, findVacancies);
        verify(vacancyRepository, times(1)).findAll();
    }

    @Test
    public void testCreateVacancy() throws Exception{
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("Sample Project");
        project.setProjectCode("PRJ001");

        Optional<Project> optionalProject = Optional.of(project);

        Candidate candidate1 = new Candidate();
        candidate1.setCandidateID(101);
        candidate1.setFirstname("Hansaka");
        candidate1.setLastname("JS");

        Candidate candidate2 = new Candidate();
        candidate2.setCandidateID(102);
        candidate2.setFirstname("Kavinda");
        candidate2.setLastname("KP");

        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate1);
        candidateList.add(candidate2);

        Vacancy vacancy = new Vacancy(1, "No-reason", "SE100", "Software Engineer", 2, VacancyStatusType.OPEN, project, candidateList);

        when(projectRepository.findById(1)).thenReturn(optionalProject);
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(vacancy);
        Vacancy saveVacancy = vacancyServiceImpl.createVacancy(1, vacancy);

        assertNotNull(saveVacancy);
        assertEquals(1, saveVacancy.getVacancyID());
        assertEquals("Hansaka", candidate1.getFirstname());
    }

    @Test
    public void testDeleteVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(1);

        when(vacancyRepository.existsById(1)).thenReturn(true);

        vacancyServiceImpl.deleteVacancy(1);

        verify(vacancyRepository, times(1)).deleteById(1);
    }

    @Test
    public void testFindById(){

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(1);

        when(vacancyRepository.findById(1)).thenReturn(Optional.of(vacancy));
        Optional<Vacancy> foundVacancy = vacancyServiceImpl.findByIdVacancy(1);
        assertTrue(foundVacancy.isPresent());
    }

    @Test
    public void testUpdateVacancy(){
        Vacancy existingVacancy = new Vacancy();
        existingVacancy.setVacancyID(1);
        existingVacancy.setJobRefCode("SE100");

        Vacancy updateVacancy = new Vacancy();
        updateVacancy.setJobRefCode("SE200");

        when(vacancyRepository.findById(1)).thenReturn(Optional.of(existingVacancy));
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(updateVacancy);

        Vacancy updatedVacancy = vacancyServiceImpl.updateVacancyById(1, updateVacancy);

        assertNotNull(updatedVacancy);
        assertEquals("SE200", updatedVacancy.getJobRefCode());
    }

    @Test
    public void testGetVacanciesByProjectId(){
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("Sample Project");
        project.setProjectCode("PRJ001");

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setVacancyID(1);
        vacancy1.setProject(project);

        Vacancy vacancy2 = new Vacancy();
        vacancy2.setVacancyID(2);
        vacancy2.setProject(project);

        List<Vacancy> listVacancy = new ArrayList<>();
        listVacancy.addAll(Arrays.asList(vacancy1, vacancy2));

        when(vacancyRepository.findByProjectProjectID(1)).thenReturn(listVacancy);
        List<Vacancy> foundVacancy = vacancyServiceImpl.getVacanciesByProjectId(1);

        assertEquals(listVacancy, foundVacancy);
    }

    @Test
    void testCreateVacancy2() {
        // Mock project and vacancy
        Project project = new Project();
        project.setProjectID(1);

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyID(1);
        vacancy.setJobRefCode("SE100");
        vacancy.setJobRole("Software Engineer");
        vacancy.setOpenings(2);
        vacancy.setStatus(VacancyStatusType.OPEN);

        // Mock project repository to return optional project
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));

        // Mock vacancy repository to return saved vacancy
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(vacancy);

        // Test createVacancy method
        Vacancy savedVacancy = vacancyServiceImpl.createVacancy(1, vacancy);

        // Verify that the project is set for the vacancy
        assertNotNull(savedVacancy.getProject());
        assertEquals(project.getProjectID(), savedVacancy.getProject().getProjectID());

        // Verify that the vacancy is saved
        assertNotNull(savedVacancy);
        assertEquals(vacancy.getJobRefCode(), savedVacancy.getJobRefCode());
        assertEquals(vacancy.getJobRole(), savedVacancy.getJobRole());
        assertEquals(vacancy.getOpenings(), savedVacancy.getOpenings());
        assertEquals(vacancy.getStatus(), savedVacancy.getStatus());

        // Verify that project repository's findById method is called
        verify(projectRepository, times(1)).findById(1);

        // Verify that vacancy repository's save method is called
        verify(vacancyRepository, times(1)).save(any(Vacancy.class));
    }

    @Test
    void testCreateVacancy_Failure_ProjectNotFound() {

        when(projectRepository.findById(anyInt())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class,
                () -> vacancyServiceImpl.createVacancy(1, new Vacancy()));
        assertEquals("Project not found with id: 1", exception.getMessage());

        verify(projectRepository, times(1)).findById(1);
        verify(vacancyRepository, never()).save(any(Vacancy.class));
    }

    @Test
    void testCreateVacancy_Failure_SaveVacancy() {

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(new Project()));
        when(vacancyRepository.save(any(Vacancy.class))).thenThrow(new RuntimeException("Failed to save vacancy"));

        Exception exception = assertThrows(Exception.class,
                () -> vacancyServiceImpl.createVacancy(1, new Vacancy()));
        assertEquals("Failed to save vacancy", exception.getMessage());


        verify(projectRepository, times(1)).findById(1);
        verify(vacancyRepository, times(1)).save(any(Vacancy.class));
    }

    @Test
    void testFindVacancy_Failure_VacancyNotFound() {

        when(vacancyRepository.findById(anyInt())).thenThrow(new IllegalArgumentException("Cannot find vacancy"));
        Exception exception = assertThrows(Exception.class,
                () -> vacancyServiceImpl.findByIdVacancy(1));
        assertEquals("Cannot find vacancy", exception.getMessage());

    }
    @Test
    void testFindVacancy_Failure_VacancyNotNull() {

        Integer vacancyId = null;

        when(vacancyRepository.findById(vacancyId)).thenThrow(new IllegalArgumentException("Vacancy ID cannot be null"));
        Exception exception = assertThrows(Exception.class,
                () -> vacancyServiceImpl.findByIdVacancy(vacancyId));
        assertEquals("Vacancy ID cannot be null", exception.getMessage());

    }

    @Test
    void testDeleteVacancy_Failure_VacancyNotFound() {

        Integer vacancyID = 1;

        when(vacancyRepository.existsById(vacancyID)).thenReturn(false);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> vacancyServiceImpl.deleteVacancy(vacancyID));

        String expectedMessage = "Vacancy not found with id: " + vacancyID;
        String actualMessage = exception.getMessage();
        assert(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testFindAllVacancies_VacanciesNotFound(){
        when(vacancyRepository.findAll()).thenThrow(new RuntimeException("Cannot fetch all vacancies"));
        RuntimeException exception = assertThrows(
                RuntimeException.class, ()-> vacancyServiceImpl.findAllVacancies());

        assertEquals("Cannot fetch all vacancies", exception.getMessage());
    }
}
