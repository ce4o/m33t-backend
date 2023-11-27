package pl.kj.m33t.m33tbackend.dto.request;

import java.util.Date;

public record EventRequest(String name, Date date, String eventType, String description) {}