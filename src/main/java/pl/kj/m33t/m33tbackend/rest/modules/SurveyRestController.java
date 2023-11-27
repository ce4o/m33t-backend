package pl.kj.m33t.m33tbackend.rest.modules;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.dto.request.modules.SurveyRequest;
import pl.kj.m33t.m33tbackend.dto.response.modules.SurveyResponse;
import pl.kj.m33t.m33tbackend.entity.modules.Survey;
import pl.kj.m33t.m33tbackend.service.modules.SurveyService;
import pl.kj.m33t.m33tbackend.mapping.modules.SurveyMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/surveys")
public class SurveyRestController {
    private final SurveyService surveyService;
    @PostMapping
    public ResponseEntity<SurveyResponse> createSurvey(@PathVariable Long eventId, @RequestBody SurveyRequest surveyRequest) {
        Survey createdSurvey = surveyService.createSurvey(eventId, surveyRequest);
        SurveyResponse surveyResponse = SurveyMapper.INSTANCE.fromSurveytoSurveyResponse(createdSurvey);
        return ResponseEntity.ok(surveyResponse);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponse> getSurveyById(@PathVariable Long surveyId) {
        return ResponseEntity.ok(SurveyMapper.INSTANCE.fromSurveytoSurveyResponse(surveyService.findById(surveyId)));
    }

    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyResponse> updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyRequest surveyRequest) {
        Survey survey = surveyService.save(SurveyMapper.INSTANCE.fromSurveyRequestToSurvey(surveyRequest));
        return ResponseEntity.ok(SurveyMapper.INSTANCE.fromSurveytoSurveyResponse(survey));
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteById(surveyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{surveyId}/vote")
    public ResponseEntity<Void> submitVote(@PathVariable Long surveyId, @RequestParam String option) {
        surveyService.submitVote(surveyId, option);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }
}