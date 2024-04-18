package com.practice.webapi.controllers;

import com.practice.webapi.models.Professor;
import com.practice.webapi.models.User;
import com.practice.webapi.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProfessor(@RequestBody CreateProfessorData data) {
        User user = new User(data.getEmail(), data.getPassword(), "ROLE_PROFESSOR");
        Professor professor = new Professor(data.getName(), data.getSurname());
        return ResponseEntity.ok(professorService.createProfessor(professor, user));
    }
}

@Getter
@Setter
@AllArgsConstructor
class CreateProfessorData {
    private String email;
    private String password;
    private String name;
    private String surname;
}