package pl.kj.m33t.m33tbackend.dto.request.modules;

import java.util.List;

public record LotteryRequest(String sentence, List<String> options) {}