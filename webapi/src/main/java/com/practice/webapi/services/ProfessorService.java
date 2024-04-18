package com.practice.webapi.services;

import com.practice.webapi.models.Professor;
import com.practice.webapi.models.User;
import com.practice.webapi.repos.ProfessorRepository;
import com.practice.webapi.repos.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ProfessorService {
    private ProfessorRepository professorRepository;
    private UserRepository userRepository;

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
}
