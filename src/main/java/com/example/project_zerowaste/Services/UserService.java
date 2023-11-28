package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Repositories.UserRepository;
import com.example.project_zerowaste.Entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public void save(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username with name exists: " + user.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        System.out.println(user);
    }

    public User findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }

    public void editUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

