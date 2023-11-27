//package pl.kj.squadki.squadkibackend;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import pl.kj.squadki.squadkibackend.dto.request.EventRequest;
//import pl.kj.squadki.squadkibackend.dto.response.EventResponse;
//import pl.kj.squadki.squadkibackend.entity.Event;
//import pl.kj.squadki.squadkibackend.entity.User;
//import pl.kj.squadki.squadkibackend.mapping.EventMapper;
//import pl.kj.squadki.squadkibackend.rest.EventRestController;
//import pl.kj.squadki.squadkibackend.service.EventService;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static java.nio.file.Paths.get;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import org.junit.*;
//import org.junit.runner.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.mock.mockito.*;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.MockitoAnnotations.initMocks;
//import static org.mockito.MockitoAnnotations.openMocks;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EventRestController.class)
//public class EventRestControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private EventService eventService;
//
//    @Test
//    public void testFindAll() throws Exception {
//
//        List<Event> events = new ArrayList<>();
//        events.add(new Event());
//        events.add(new Event());
//
//        given(eventService.findAll()).willReturn(events);
//
//        RequestBuilder request = MockMvcRequestBuilders
//                .get("/events")
//                .accept(MediaType.APPLICATION_JSON);
//
//        MockHttpServletResponse response = mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(
//                jsonSuperHero.write(new SuperHero("Rob", "Mannon", "RobotMan")).getJson()
//        );
//
//    }
//
//    @Test
//    public void testGetEvent() {
//        Long eventId = 1L;
//        Event event = new Event();
//
//        when(eventService.findById(eventId)).thenReturn(event);
//
//        ResponseEntity<EventResponse> responseEntity = eventRestController.getEvent(eventId);
//
//        verify(eventService, times(1)).findById(eventId);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateEvent() {
//        EventRequest eventRequest = new EventRequest();
//        Event unsavedEvent = EventMapper.INSTANCE.fromEventRequestToEvent(eventRequest);
//        Event savedEvent = new Event();
//
//        when(eventService.save(unsavedEvent)).thenReturn(savedEvent);
//
//        ResponseEntity<EventResponse> responseEntity = eventRestController.createEvent(eventRequest);
//
//        verify(eventService, times(1)).save(unsavedEvent);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//    }
//
//    @Test
//    public void testUpdateEvent() {
//        Long eventId = 1L;
//        EventRequest eventRequest = new EventRequest();
//        Event updatedEvent = new Event();
//
//        when(eventService.save(any())).thenReturn(updatedEvent);
//
//        ResponseEntity<EventResponse> responseEntity = eventRestController.updateEvent(eventId, eventRequest);
//
//        verify(eventService, times(1)).save(any());
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//    }
//
//    @Test
//    public void testDeleteEvent() {
//        Long eventId = 1L;
//
//        doNothing().when(eventService).deleteById(eventId);
//
//        ResponseEntity<Void> responseEntity = eventRestController.deleteEvent(eventId);
//
//        verify(eventService, times(1)).deleteById(eventId);
//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//    }
//}
