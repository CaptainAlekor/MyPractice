package com.practice.webapi.controllers;

import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.services.GroupCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("groupCourses")
public class GroupCourseController {
    private GroupCourseService groupCourseService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupCourse> getGroupCourse(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(groupCourseService.getGroupCourseById(id));
    }
}
