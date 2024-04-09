package com.practice.webapi.controllers;

import com.practice.webapi.services.UserService;
import com.practice.webapi.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> sayok() {
        return ResponseEntity.ok("very good and very nice");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int id) {
        System.out.println(userService.getUserById(id));
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
