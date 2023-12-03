package pl.kj.m33t.m33tbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kj.m33t.m33tbackend.config.JwtService;
import pl.kj.m33t.m33tbackend.service.dto.request.AuthenticationRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.AuthenticationResponse;
import pl.kj.m33t.m33tbackend.model.repository.UserRepository;
import pl.kj.m33t.m33tbackend.model.entity.Role;
import pl.kj.m33t.m33tbackend.model.entity.User;
import pl.kj.m33t.m33tbackend.service.dto.request.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        User user = new User(1L,registerRequest.getNickname(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                Role.USER);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}