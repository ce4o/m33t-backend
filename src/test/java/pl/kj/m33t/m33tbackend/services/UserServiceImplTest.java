package pl.kj.m33t.m33tbackend.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kj.m33t.m33tbackend.dao.UserRepository;
import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findById(userId);

        verify(userRepository).findById(userId);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        User user = new User();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.findByEmail(email);

        verify(userRepository).findByEmail(email);
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void testSave() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        verify(userRepository).save(user);
        assertNotNull(result);
        assertEquals(user, result);
    }

//    @Test
//    public void testGetLoggedUser() {
//        String loggedInUsername = "test@test.com";
//        Authentication authentication = Mockito.mock(Authentication.class);
//        when(authentication.getName()).thenReturn(loggedInUsername);
//        User user = new User();
//
//        when(userRepository.findByEmail(loggedInUsername)).thenReturn(Optional.of(user));
//
//        User result = userService.getLoggedUser(authentication);
//
//        verify(userRepository).findByEmail(loggedInUsername);
//        assertNotNull(result);
//        assertEquals(user, result);
//    }

    @Test
    public void testFindAll() {
        List<User> userList = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        verify(userRepository).findAll();
        assertNotNull(result);
        assertEquals(userList, result);
    }

    @Test
    public void testDeleteById() {
        Long userId = 1L;
        userService.deleteById(userId);

        verify(userRepository).deleteById(userId);
    }
}
