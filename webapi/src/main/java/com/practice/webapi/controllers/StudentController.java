package com.practice.webapi.controllers;

import com.practice.webapi.models.Student;
import com.practice.webapi.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
