package pl.kj.m33t.m33tbackend.dto.response;

import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.entity.modules.EventModule;

import java.util.Date;
import java.util.List;
public record EventResponse(Long id, User owner, String name, Date date, String eventType, List<UserResponse> participants, String description, List<EventModule> eventModulesList) {}