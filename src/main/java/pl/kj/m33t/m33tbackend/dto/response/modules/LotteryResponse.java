package pl.kj.m33t.m33tbackend.dto.response.modules;

import java.util.Date;
import java.util.List;

public record LotteryResponse (Long lotteryId, Long eventId, String sentence, List<String> options, String result, Date date) {}