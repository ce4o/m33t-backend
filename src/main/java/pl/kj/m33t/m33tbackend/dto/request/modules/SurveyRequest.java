package pl.kj.m33t.m33tbackend.dto.request.modules;

import java.util.HashMap;
import java.util.List;

public record SurveyRequest(String sentence, List<String> options) {}