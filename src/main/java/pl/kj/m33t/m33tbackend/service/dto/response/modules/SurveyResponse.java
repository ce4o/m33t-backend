package pl.kj.m33t.m33tbackend.service.dto.response.modules;

import java.util.Date;
import java.util.Map;

public record SurveyResponse(Long postId, Long eventId, String sentence, Map<String, Integer> optionsAndVotes, Date date) {}