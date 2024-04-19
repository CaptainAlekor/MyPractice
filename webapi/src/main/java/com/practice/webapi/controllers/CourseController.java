package com.practice.webapi.controllers;

import com.practice.webapi.models.Course;
import com.practice.webapi.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestBody String name) {
        Course course = new Course(name);
        return ResponseEntity.ok(courseService.createCourse(course));
    }
}
