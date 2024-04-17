package com.practice.webapi.controllers;

import com.practice.webapi.models.Student;
import com.practice.webapi.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping(params = "groupId")
    public ResponseEntity<List<Student>> getStudentsByGroupId(@RequestParam int groupId) {
        return ResponseEntity.ok(studentService.getStudentsByGroupId(groupId));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
