package pl.kj.m33t.m33tbackend.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.dto.request.AuthenticationRequest;
import pl.kj.m33t.m33tbackend.dto.response.AuthenticationResponse;
import pl.kj.m33t.m33tbackend.dto.request.RegisterRequest;
import pl.kj.m33t.m33tbackend.service.AuthenticationService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/event")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}