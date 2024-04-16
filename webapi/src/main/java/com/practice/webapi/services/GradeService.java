package com.practice.webapi.services;

import com.practice.webapi.models.Grade;
import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.models.Student;
import com.practice.webapi.repos.GradeRepository;
import com.practice.webapi.repos.GroupCourseRepository;
import com.practice.webapi.repos.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class GradeService {
    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private GroupCourseRepository groupCourseRepository;

    public List<Grade> getGradesByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return gradeRepository.getGradesByStudentIdAndGroupCourse_CourseId(studentId, courseId);
    }

    public void updateGradeValueById(int id, int value) {
        gradeRepository.updateGradeValueById(id, value);
    }

    public void createGrade(Grade grade, int studentId, int groupCourseId) {
        Student student;
        GroupCourse groupCourse;

        Optional<Student> optStudent = studentRepository.findById(studentId);
        if (optStudent.isPresent()) {
            student = optStudent.get();
        } else return;

        Optional<GroupCourse> optGroupCourse = groupCourseRepository.findById(groupCourseId);
        if (optGroupCourse.isPresent()) {
            groupCourse = optGroupCourse.get();
        } else return;

        grade.setStudent(student);
        grade.setGroupCourse(groupCourse);
        gradeRepository.save(grade);
    }
    public void deleteGradeById(int id) {
        gradeRepository.deleteGradeById(id);
    }
}
