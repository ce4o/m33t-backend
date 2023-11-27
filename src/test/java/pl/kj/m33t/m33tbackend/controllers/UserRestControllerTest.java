//package pl.kj.squadki.squadkibackend;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import pl.kj.squadki.squadkibackend.dto.request.UserRequest;
//import pl.kj.squadki.squadkibackend.entity.User;
//import pl.kj.squadki.squadkibackend.rest.UserRestController;
//import pl.kj.squadki.squadkibackend.service.UserService;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static net.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserRestController.class)
//public class UserRestControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        User user1 = new User(1L, "John", "Doe");
//        User user2 = new User(2L, "Jane", "Smith");
//
//        List<User> users = Arrays.asList(user1, user2);
//
//        when(userService.findAll()).thenReturn(users);
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].firstName", is("John")))
//                .andExpect(jsonPath("$[0].lastName", is("Doe")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].firstName", is("Jane")))
//                .andExpect(jsonPath("$[1].lastName", is("Smith")));
//
//        verify(userService, times(1)).findAll();
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testGetUserById() throws Exception {
//        Long userId = 1L;
//        User user = new User(userId, "John", "Doe");
//
//        when(userService.findById(userId)).thenReturn(user);
//
//        mockMvc.perform(get("/users/{userId}", userId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("John")))
//                .andExpect(jsonPath("$.lastName", is("Doe")));
//
//        verify(userService, times(1)).findById(userId);
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testCreateUser() throws Exception {
//        UserRequest userRequestDto = new UserRequest("John", "Doe", "jane@gmail.com");
//        User user = new User(1L, userRequestDto.firstname(), userRequestDto.lastname(), userRequestDto.email());
//
//        when(userService.save(any(User.class))).thenReturn(user);
//
//        String requestJson = new ObjectMapper().writeValueAsString(userRequestDto);
//
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("John")))
//                .andExpect(jsonPath("$.lastName", is("Doe")));
//
//        verify(userService, times(1)).save(any(User.class));
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testUpdateUser() throws Exception {
//        Long userId = 1L;
//        UserRequest userRequestDto = new UserRequest("Jane", "Smith", "jane@gmail.com");
//        User updatedUser = new User(userId, userRequestDto.firstname(), userRequestDto.lastname(), userRequestDto.email());
//
//        when(userService.save(any(User.class))).thenReturn(updatedUser);
//        when(userService.findById(userId)).thenReturn(updatedUser);
//
//        String requestJson = new ObjectMapper().writeValueAsString(userRequestDto);
//
//        mockMvc.perform(put("/users/{userId}", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("Jane")))
//                .andExpect(jsonPath("$.lastName", is("Smith")));
//
//        verify(userService, times(1)).save(any(User.class));
//        verify(userService, times(1)).findById(userId);
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        Long userId = 1L;
//
//        mockMvc.perform(delete("/users/{userId}", userId))
//                .andExpect(status().isNoContent());
//
//        verify(userService, times(1)).deleteById(userId);
//        verifyNoMoreInteractions(userService);
//    }
//
//}
//
