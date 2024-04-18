package com.practice.webapi.services;

import com.practice.webapi.models.Group;
import com.practice.webapi.models.Student;
import com.practice.webapi.models.User;
import com.practice.webapi.repos.GroupRepository;
import com.practice.webapi.repos.StudentRepository;
import com.practice.webapi.repos.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        return studentRepository.findStudentsByGroup_Id(groupId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String createStudent(Student student, User user, String groupName) {
        Group group = groupRepository.findByName(groupName).orElse(null);
        if (group == null) {
            return "Group " + groupName + " does not exist";
        }
        userRepository.save(user);
        student.setUser(user);
        user.setPerson(student);
        student.setGroup(group);
        group.getStudents().add(student);
        studentRepository.save(student);
        return "Student created";
    }
}
