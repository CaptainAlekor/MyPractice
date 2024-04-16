package com.practice.webapi.controllers;

import com.practice.webapi.models.Grade;
import com.practice.webapi.services.GradeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grades")
public class GradeController {
    private GradeService gradeService;

    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByStudentIdAndCourseId(@PathVariable int studentId, @PathVariable int courseId) {
        return ResponseEntity.ok(gradeService.getGradesByStudentIdAndCourseId(studentId, courseId));
    }

    @PutMapping("/updateGradeValue")
    public ResponseEntity<String> updateGradeValue(@RequestBody UpdateData updateData) {
        gradeService.updateGradeValueById(updateData.getId(), updateData.getValue());
        return ResponseEntity.ok("Grade value updated successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGrade(@RequestBody CreateData createData) {
        Grade grade = new Grade(createData.getValue(), createData.getDate());
        gradeService.createGrade(grade, createData.getStudentId(), createData.getGroupCourseId());
        System.out.println(createData);
        return ResponseEntity.ok("Grade created successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable int id) {
        gradeService.deleteGradeById(id);
        return ResponseEntity.ok("Grade deleted successfully");
    }
}

@Setter
@Getter
@AllArgsConstructor
class UpdateData {
    private int id;
    private int value;
}

@Setter
@Getter
@AllArgsConstructor
class CreateData {
    private int value;
    private String date;
    private int studentId;
    private int groupCourseId;

    @Override
    public String toString() {
        return "CreateData{" +
                "value=" + value +
                ", date='" + date + '\'' +
                ", studentId=" + studentId +
                ", groupCourseId=" + groupCourseId +
                '}';
    }
}
