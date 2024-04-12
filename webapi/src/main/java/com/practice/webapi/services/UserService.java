package com.practice.webapi.services;


import com.practice.webapi.models.User;
import com.practice.webapi.repos.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserService {
    private UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
