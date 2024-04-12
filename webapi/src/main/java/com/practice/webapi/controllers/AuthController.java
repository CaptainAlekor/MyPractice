package com.practice.webapi.controllers;

import com.practice.webapi.models.User;
import com.practice.webapi.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody AuthData authData) {
        User user = userService.getUserByEmail(authData.getEmail());
        if (user == null) {
            return ResponseEntity.ok("User with email " + authData.getEmail() + " not found");
        }
        if (!user.getPassword().equals(authData.getPassword())) {
            return ResponseEntity.ok("Wrong password");
        }
        return ResponseEntity.ok(user);
    }
}

@Setter
@Getter
@AllArgsConstructor
class AuthData {
    private String email;
    private String password;
}