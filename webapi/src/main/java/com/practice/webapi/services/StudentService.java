package com.practice.webapi.services;

import com.practice.webapi.models.Student;
import com.practice.webapi.repos.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        return studentRepository.findStudentsByGroup_Id(groupId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
