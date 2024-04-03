package services;

import dao.UserDAO;
import models.User;

import java.lang.reflect.Constructor;

public class UserService {
    private static final UserDAO userDAO = new UserDAO();

    private UserService() {}

    public static void createUser(User user) {
        userDAO.create(user);
    }
    public static User findUser(int id) {
        return userDAO.read(id);
    }
    public static User findUser(String email) {
        return userDAO.read(email);
    }
    public static void updateUser(User user) {
        userDAO.update(user);
    }
    public static void deleteUser(User user) {
        userDAO.delete(user);
    }
}
