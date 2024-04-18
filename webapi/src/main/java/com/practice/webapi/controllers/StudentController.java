package com.practice.webapi.controllers;

import com.practice.webapi.models.Student;
import com.practice.webapi.models.User;
import com.practice.webapi.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody StudentCreateData studentCreateData) {
        User user = new User(studentCreateData.getEmail(), studentCreateData.getPassword(), "ROLE_STUDENT");
        Student student = new Student(studentCreateData.getName(), studentCreateData.getSurname());
        String result = studentService.createStudent(student, user, studentCreateData.getGroupName());
        return ResponseEntity.ok(result);
    }
}

@Setter
@Getter
@AllArgsConstructor
class StudentCreateData {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String groupName;

    @Override
    public String toString() {
        return "StudentCreateData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}