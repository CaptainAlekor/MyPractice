package main;

import services.UserService;
import models.*;

public class Main {
    public static void main(String[] args) {
        User user = UserService.findUser(2);
        Professor professor = new Professor("name", "surname");
        user.setPerson(professor);
        professor.setUser(user);
        UserService.updateUser(user);
    }
}

