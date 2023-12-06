package pl.kj.m33t.m33tbackend.service.dto.request.modules;

import java.util.List;

public record SurveyRequest(String sentence, List<String> options) {}