package pl.kj.m33t.m33tbackend.service;

import org.springframework.security.core.Authentication;
import pl.kj.m33t.m33tbackend.model.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    User getLoggedUser(Authentication authentication);

    List<User> findAll();

    void deleteById(Long id);
}