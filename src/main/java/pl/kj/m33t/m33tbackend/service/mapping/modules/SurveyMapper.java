package pl.kj.m33t.m33tbackend.service.mapping.modules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.SurveyRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.modules.SurveyResponse;
import pl.kj.m33t.m33tbackend.model.entity.modules.Survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SurveyMapper {
    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);
    @Mapping(target = "postId", source = "id")
    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "sentence", source = "sentence")
    @Mapping(target = "optionsAndVotes", source = "optionsAndVotes")
    @Mapping(target = "date", source = "date")
    SurveyResponse fromSurveytoSurveyResponse(Survey survey);

    @Mapping(target = "sentence", source = "sentence")
    @Mapping(target = "optionsAndVotes", source = "options")
    Survey fromSurveyRequestToSurvey(SurveyRequest surveyRequest);

    default Map<String, Integer> mapOptions(List<String> options) {
        Map<String, Integer> optionsAndVotes = new HashMap<>();
        for (String option : options) {
            optionsAndVotes.put(option, 0);
        }
        return optionsAndVotes;
    }

}