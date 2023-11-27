package pl.kj.m33t.m33tbackend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.kj.m33t.m33tbackend.dao.EventRepository;
import pl.kj.m33t.m33tbackend.entity.Event;
import pl.kj.m33t.m33tbackend.entity.EventType;
import pl.kj.m33t.m33tbackend.entity.Role;
import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.entity.modules.EventModule;
import pl.kj.m33t.m33tbackend.service.EventServiceImpl;
import pl.kj.m33t.m33tbackend.service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long eventId = 1L;
        Event event = new Event(eventId, new User(), "test name", new Date(), EventType.CAMPFIRE, new ArrayList<User>(), new ArrayList<EventModule>(), "desc");

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Event result = eventService.findById(eventId);

        assertEquals(event, result);
        verify(eventRepository, times(1)).findById(eventId);
    }

//    @Test
//    void testFindById_NotFound() {
//        Long eventId = 1L;
//
//        when(eventRepository.findById(eventId)).thenThrow(EventNotFoundException.class);
//
//        assertThrows(EventNotFoundException.class, () -> eventService.findById(eventId));
//        verify(eventRepository, times(1)).findById(eventId);
//    }

    @Test
    void testSave() {
        Long eventId = 1L;
        Event event = new Event(eventId, null , "test name", new Date(), EventType.CAMPFIRE, new ArrayList<User>(), new ArrayList<EventModule>(), "desc");
        User loggedUser = new User(1L, "test", "test", "test", Role.USER);

        when(eventRepository.save(event)).thenReturn(event);
        when(userService.getLoggedUser((SecurityContextHolder.getContext().getAuthentication()))).thenReturn(loggedUser);

        Event result = eventService.save(event);

        assertEquals(event, result);
        assertEquals(loggedUser, event.getOwner());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testFindAll() {
        List<Event> events = Arrays.asList(new Event(), new Event());

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.findAll();

        assertEquals(events, result);
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        Long eventId = 1L;
        Event event = new Event(eventId, null , "test name", new Date(), EventType.CAMPFIRE, new ArrayList<User>(), new ArrayList<EventModule>(), "desc");

        eventService.deleteById(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }
}
