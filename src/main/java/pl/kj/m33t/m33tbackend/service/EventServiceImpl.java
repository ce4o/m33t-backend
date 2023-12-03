package pl.kj.m33t.m33tbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kj.m33t.m33tbackend.model.repository.EventRepository;
import pl.kj.m33t.m33tbackend.model.entity.Event;
import pl.kj.m33t.m33tbackend.model.entity.User;
import pl.kj.m33t.m33tbackend.exception.EventNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserService userService;
    private final EventRepository eventRepository;

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
    }

    @Override
    public Event save(Event event) {
        event.setOwner(userService.getLoggedUser(SecurityContextHolder.getContext().getAuthentication()));
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void joinEvent(Long eventId) {
        findById(eventId).addParticipant(
                userService.getLoggedUser(SecurityContextHolder.getContext().getAuthentication()));
    }

}