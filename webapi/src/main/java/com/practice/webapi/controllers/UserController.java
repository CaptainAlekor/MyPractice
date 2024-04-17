package com.practice.webapi.controllers;

import com.practice.webapi.services.UserService;
import com.practice.webapi.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> sayok() {
        return ResponseEntity.ok("very good and very nice");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
