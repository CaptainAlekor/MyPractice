package com.practice.webapi.controllers;

import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.services.GroupCourseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("groupCourses")
public class GroupCourseController {
    private GroupCourseService groupCourseService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupCourse> getGroupCourse(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(groupCourseService.getGroupCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<GroupCourse>> getAllGroupCourses() {
        return ResponseEntity.ok(groupCourseService.getAllGroupCourses());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroupCourse(@RequestBody CreateGroupCourseData data) {
        return ResponseEntity.ok(groupCourseService.createGroupCourse(data.getGroupName(), data.getCourseId()));
    }
}

@Getter
@Setter
@AllArgsConstructor
class CreateGroupCourseData {
    private String groupName;
    private int courseId;
}