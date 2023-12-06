package pl.kj.m33t.m33tbackend.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.model.entity.User;
import pl.kj.m33t.m33tbackend.service.UserService;
import pl.kj.m33t.m33tbackend.service.dto.request.UserRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.UserResponse;
import pl.kj.m33t.m33tbackend.service.mapping.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(UserMapper.INSTANCE::fromUserToUserResponse)
                .collect(Collectors.toList());
        return  ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(UserMapper.INSTANCE.fromUserToUserResponse(userService.findById(userId)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        User unsavedUser = UserMapper.INSTANCE.fromUserRequestToUser(userRequest);
        User savedUser = userService.save(unsavedUser);
        return new ResponseEntity<>(UserMapper.INSTANCE.fromUserToUserResponse(savedUser), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User existingUser = userService.findById(userId);
        User updatedUser = UserMapper.INSTANCE.fromUserRequestToUser(userRequest);
        updatedUser.setId(existingUser.getId());
        User savedUser = userService.save(updatedUser);
        return ResponseEntity.ok(UserMapper.INSTANCE.fromUserToUserResponse(savedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}