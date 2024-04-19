package com.practice.webapi.services;

import com.practice.webapi.models.Group;
import com.practice.webapi.models.GroupCourse;
import com.practice.webapi.models.Professor;
import com.practice.webapi.models.User;
import com.practice.webapi.repos.GroupCourseRepository;
import com.practice.webapi.repos.ProfessorRepository;
import com.practice.webapi.repos.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProfessorService {
    private ProfessorRepository professorRepository;
    private UserRepository userRepository;
    private GroupCourseRepository groupCourseRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public String createProfessor(Professor professor, User user) {
        userRepository.save(user);
        professor.setUser(user);
        user.setPerson(professor);
        professorRepository.save(professor);
        return "Professor created";
    }

    public String setProfessorGroupCourse(int professorId, int groupCourseId) {
        Optional<Professor> professorOpt = professorRepository.findById(professorId);
        if (!professorOpt.isPresent()) {
            return "Professor not found";
        }
        Professor professor = professorOpt.get();
        Optional<GroupCourse> groupCourseOpt = groupCourseRepository.findById(groupCourseId);
        if (!groupCourseOpt.isPresent()) {
            return "GroupCourse not found";
        }
        try {
            GroupCourse groupCourse = groupCourseOpt.get();
            professor.getGroupCourses().add(groupCourse);
            professorRepository.save(professor);
            return "Professor group-course set";
        } catch (Exception e) {
            return "Professor group-course not set: " + e.getMessage();
        }
    }
}
