package pl.kj.m33t.m33tbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kj.m33t.m33tbackend.dao.UserRepository;
import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }
    @Override
    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User getLoggedUser(Authentication authentication){
        if(authentication == null){
            throw new IllegalStateException("No authentication data");
        }
        String loggedInUsername = authentication.getName();
        return findByEmail(loggedInUsername);
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteById(Long id){
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("DELETE FAILED - User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}