package pl.kj.m33t.m33tbackend.service;

import pl.kj.m33t.m33tbackend.model.entity.Event;

import java.util.List;

public interface EventService {
        Event findById(Long id);

        Event save(Event event);

        List<Event> findAll();

        void deleteById(Long id);

    void joinEvent(Long eventId);
}