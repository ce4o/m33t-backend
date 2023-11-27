package pl.kj.m33t.m33tbackend.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.dto.request.EventRequest;
import pl.kj.m33t.m33tbackend.dto.response.EventResponse;
import pl.kj.m33t.m33tbackend.entity.Event;
import pl.kj.m33t.m33tbackend.mapping.EventMapper;
import pl.kj.m33t.m33tbackend.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventRestController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eventRequest) {
        Event unsavedEvent = EventMapper.INSTANCE.fromEventRequestToEvent(eventRequest);
        Event savedProduct = eventService.save(unsavedEvent);
        return ResponseEntity.ok(EventMapper.INSTANCE.fromEventToEventResponse(savedProduct));
    }
    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(EventMapper.INSTANCE.fromEventToEventResponse(eventService.findById(eventId)));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable Long eventId, @RequestBody EventRequest eventRequest) {
        Event event = eventService.save(EventMapper.INSTANCE.fromEventRequestToEvent(eventRequest));
        EventResponse response = EventMapper.INSTANCE.fromEventToEventResponse(event);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteById(eventId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/join")
    public ResponseEntity<Void> joinEvent(@PathVariable Long eventId) {
        eventService.joinEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}