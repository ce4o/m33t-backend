package pl.kj.m33t.m33tbackend.service.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kj.m33t.m33tbackend.dto.request.modules.SurveyRequest;
import pl.kj.m33t.m33tbackend.entity.Event;
import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.entity.modules.Survey;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.dao.modules.SurveyRepository;
import pl.kj.m33t.m33tbackend.mapping.modules.SurveyMapper;
import pl.kj.m33t.m33tbackend.service.UserService;

@Service
@RequiredArgsConstructor
public class SurveyService {

        private final SurveyRepository surveyRepository;
        private final EventService eventService;
        private final UserService userService;

        public Survey findById(Long id){
            return surveyRepository.findById(id).orElse(null);
        }

        @Transactional
        public Survey createSurvey(Long eventId, SurveyRequest surveyRequest){
            Event event = eventService.findById(eventId);
            Survey newSurvey = SurveyMapper.INSTANCE.fromSurveyRequestToSurvey(surveyRequest);
            newSurvey.setEvent(event);
            return surveyRepository.save(newSurvey);
        }

        public Survey save(Survey survey) {
            return surveyRepository.save(survey);
        }

        @Transactional
        public void deleteById(Long id){
            surveyRepository.deleteById(id);
        }

        @Transactional
        public void submitVote(Long surveyId, String option) {
            User loggedInUser = userService.getLoggedUser(SecurityContextHolder.getContext().getAuthentication());
            Survey survey = findById(surveyId);
            if (!survey.getVoters().contains(loggedInUser.getId())) {
                survey.getOptionsAndVotes().compute(option, (key, oldValue) -> oldValue != null ? oldValue + 1 : 1);
                survey.getVoters().add(loggedInUser.getId());
                surveyRepository.save(survey);
            } else {
                throw new IllegalStateException("User has already voted in this survey.");
            }
        }
}