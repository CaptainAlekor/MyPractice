package com.practice.webapi.controllers;

import com.practice.webapi.models.Grade;
import com.practice.webapi.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grades")
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/{id}/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByStudentIdAndCourseId(@PathVariable int id, @PathVariable int courseId) {
        return ResponseEntity.ok(gradeService.getGradesByStudentIdAndCourseId(id, courseId));
    }
}
