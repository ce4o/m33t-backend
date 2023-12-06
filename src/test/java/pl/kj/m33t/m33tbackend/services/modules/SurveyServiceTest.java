package pl.kj.m33t.m33tbackend.services.modules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.kj.m33t.m33tbackend.model.entity.Event;
import pl.kj.m33t.m33tbackend.model.entity.User;
import pl.kj.m33t.m33tbackend.model.entity.modules.Survey;
import pl.kj.m33t.m33tbackend.model.repository.modules.SurveyRepository;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.service.UserService;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.SurveyRequest;
import pl.kj.m33t.m33tbackend.service.modules.SurveyService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SurveyServiceTest {

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long surveyId = 1L;
        Survey survey = new Survey();
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

        Survey result = surveyService.findById(surveyId);

        verify(surveyRepository).findById(surveyId);
        assertNotNull(result);
        assertEquals(survey, result);
    }

    @Test
    public void testCreateSurvey() {
        Long eventId = 1L;
        SurveyRequest surveyRequest = new SurveyRequest("Test?", new ArrayList<>());

        Event event = new Event();
        when(eventService.findById(eventId)).thenReturn(event);

        Survey newSurvey = new Survey();
        when(surveyRepository.save(any(Survey.class))).thenReturn(newSurvey);

        Survey result = surveyService.createSurvey(eventId, surveyRequest);

        verify(eventService).findById(eventId);
        verify(surveyRepository).save(any(Survey.class));
        assertNotNull(result);
        assertEquals(newSurvey, result);
    }

    @Test
    public void testSave() {
        Survey survey = new Survey();
        when(surveyRepository.save(survey)).thenReturn(survey);

        Survey result = surveyService.save(survey);

        verify(surveyRepository).save(survey);
        assertNotNull(result);
        assertEquals(survey, result);
    }

    @Test
    public void testDeleteById() {
        Long surveyId = 1L;
        surveyService.deleteById(surveyId);

        verify(surveyRepository).deleteById(surveyId);
    }

    @Test
    public void testSubmitVote() {
        Long surveyId = 1L;
        String option = "Option A";
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String loggedInUsername = "test@example.com";
        User loggedInUser = new User();
        Survey survey = new Survey();

        when(userService.getLoggedUser(authentication)).thenReturn(loggedInUser);
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

        surveyService.submitVote(surveyId, option);

        verify(surveyRepository).findById(surveyId);
        verify(surveyRepository).save(survey);
        assertTrue(survey.getVoters().contains(loggedInUser.getId()));
        assertEquals(1, survey.getOptionsAndVotes().get(option));
    }

    @Test
    public void testSubmitVoteWithExistingVote() {
        Long surveyId = 1L;
        String option = "Option A";
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String loggedInUsername = "test@example.com";
        User loggedInUser = new User();
        Survey survey = new Survey();

        when(userService.getLoggedUser(authentication)).thenReturn(loggedInUser);
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

        // Simulate the user already voted for the same option
        survey.getVoters().add(loggedInUser.getId());
        survey.getOptionsAndVotes().put(option, 1);

        assertThrows(IllegalStateException.class, () -> surveyService.submitVote(surveyId, option));

        verify(surveyRepository).findById(surveyId);
        // The repository should not be called when the user has already voted
        verify(surveyRepository, never()).save(survey);
    }
}
