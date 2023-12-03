package pl.kj.m33t.m33tbackend.service.dto.response.modules;

import java.util.Date;

public record PostResponse(Long postId, Long eventId, String title, String description, Date date) {}