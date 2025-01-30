package com.campus.ledrian.user.application;

import com.campus.ledrian.user.domain.RegisterUserDTO;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;  
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User register(RegisterUserDTO registerUserDTO) {

        User User = new User();
        User.setName(registerUserDTO.getName());
        User.setLastname(registerUserDTO.getLastname());
        User.setEmail(registerUserDTO.getEmail());
        User.setUsername(registerUserDTO.getUsername());
        User.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        return userRepository.save(User);
    }

    public boolean verifyByEmail(String email, String password) {
        User User = getUserByEmail(email);
        if (User == null) {
            return false;
        }
        return passwordEncoder.matches(password, User.getPassword());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
