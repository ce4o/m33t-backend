package pl.kj.m33t.m33tbackend.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.service.dto.request.EventRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.EventResponse;
import pl.kj.m33t.m33tbackend.model.entity.Event;

import java.util.List;

//TODO: CQRS?
//TODO: DDD?
//TODO: microservices? :) - discovery service, gateway, configuration server, LB (loadbalancing)

@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public interface EventRestControllerApi {
    @PostMapping
    ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eventRequest);

    @GetMapping
    List<Event> findAll();

    //TODO: delete, refactor and return the deleted object
}
