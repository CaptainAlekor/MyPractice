package tools;

import entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CRUDAssistant implements AutoCloseable {
    private final DBHelper dbHelper;

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GROUP_ID = "group_id";
    private static final String USER_ID = "user_id";
    private static final String VALUE = "value";
    private static final String DATE = "date";
    private static final String STUDENT_ID = "student_id";
    private static final String COURSE_ID = "course_id";
    private static final String PROFESSOR_ID = "professor_id";


    public CRUDAssistant() {
        dbHelper = new DBHelper();
    }

    public static void handleSQLException(SQLException e) {
        System.out.println("SQL error occurred");
        System.out.println("Info: " + e.getMessage());
        System.out.println("Stacktrace: " + Arrays.toString(e.getStackTrace()));
    }

    // User CRUD
    public void createUser(String email, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into users (email, password) values (?, ?);")) {
            statement.setString(1, email);
            statement.setString(2, password);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<User> readAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, email, password from users;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                users.add(new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD)
                ));
            }

            return users;
        } catch (SQLException e) {
            handleSQLException(e);
            return users;
        }
    }
    public User readUserById(int id) {
        User user = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, email, password from users where id = ?;")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD));
            }

            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return user;
        }
    }
    public User readUserByEmail(String email) {
        User user = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, email, password from users where email = ?;")) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD));
            }

            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return user;
        }
    }
    public void updateUserById(int id, String email, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update users set email = ?, password = ? where id = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateUserEmailById(int id, String email) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update users set email = ? where id = ?")) {
            statement.setString(1, email);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateUserPasswordById(int id, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update users set password = ? where id = ?")) {
            statement.setString(1, password);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteUserById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from users where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Student CRUD
    public void createStudent(String name, String surname, int groupId, int userId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into students (name, surname, group_id, user_id) values (?, ?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, groupId);
            statement.setInt(4, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<Student> readAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, group_id, user_id from students;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                students.add(new Student(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(GROUP_ID),
                        result.getInt(USER_ID)
                ));
            }

            return students;
        } catch (SQLException e) {
            handleSQLException(e);
            return students;
        }
    }
    public List<Student> readStudentsByGroupId(int groupId) {
        ArrayList<Student> students = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, group_id, user_id from students where group_id = ?;")) {
            statement.setInt(1, groupId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                students.add(new Student(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(GROUP_ID),
                        result.getInt(USER_ID)
                ));
            }

            return students;
        } catch (SQLException e) {
            handleSQLException(e);
            return students;
        }
    }
    public Student readStudentById(int id) {
        Student student = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, group_id, user_id from students where id = ?")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                student = new Student(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(GROUP_ID),
                        result.getInt(USER_ID)
                );
            }

            return student;
        } catch (SQLException e) {
            handleSQLException(e);
            return student;
        }
    }
    public Student readStudentByNameSurname(String name, String surname) {
        Student student = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, group_id, user_id from students where name = ? and surname = ?;")) {
            statement.setString(1, name);
            statement.setString(2, surname);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                student = new Student(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(GROUP_ID),
                        result.getInt(USER_ID)
                );
            }

            return student;
        } catch (SQLException e) {
            handleSQLException(e);
            return student;
        }
    }
    public Student readStudentByUserId(int userId) {
        Student student = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, group_id, user_id from students where user_id = ?")) {
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                student = new Student(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(GROUP_ID),
                        result.getInt(USER_ID)
                );
            }

            return student;
        } catch (SQLException e) {
            handleSQLException(e);
            return student;
        }
    }
    public void updateStudentById(int id, String name, String surname, int groupId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set name = ?, surname = ?, group_id = ? where id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, groupId);
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateStudentByUserId(int userId, String name, String surname, int groupId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set name = ?, surname = ?, group_id = ? where user_id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, groupId);
            statement.setInt(4, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateStudentNameSurnameById(int id, String name, String surname) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set name = ?, surname = ? where id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateStudentNameSurnameByUserId(int userId, String name, String surname) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set name = ?, surname = ? where user_id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateStudentGroupIdById(int id, int groupId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set group_id = ? where id = ?")) {
            statement.setInt(1, groupId);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateStudentGroupIdByUserId(int userId, int groupId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update students set group_id = ? where user_id = ?")) {
            statement.setInt(1, groupId);
            statement.setInt(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteStudentById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from students where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteStudentByUserId(int userId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from students where user_id = ?")) {
            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Professor CRUD
    public void createProfessor(String name, String surname, int userId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into professors (name, surname, user_id) values (?, ?, ?);")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<Professor> readAllProfessors() {
        ArrayList<Professor> professors = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, user_id from professors;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                professors.add(new Professor(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(USER_ID)
                ));
            }

            return professors;
        } catch (SQLException e) {
            handleSQLException(e);
            return professors;
        }
    }
    public Professor readProfessorById(int id) {
        Professor professor = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, user_id from professors where id = ?")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                professor = new Professor(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(USER_ID)
                );
            }

            return professor;
        } catch (SQLException e) {
            handleSQLException(e);
            return professor;
        }
    }
    public Professor readProfessorByNameSurname(String name, String surname) {
        Professor professor = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, user_id from professors where name = ? and surname = ?;")) {
            statement.setString(1, name);
            statement.setString(2, surname);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                professor = new Professor(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(USER_ID)
                );
            }

            return professor;
        } catch (SQLException e) {
            handleSQLException(e);
            return professor;
        }
    }
    public Professor readProfessorByUserId(int userId) {
        Professor professor = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name, surname, user_id from professors where user_id = ?")) {
            statement.setInt(1, userId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                professor = new Professor(
                        result.getInt(ID),
                        result.getString(NAME),
                        result.getString(SURNAME),
                        result.getInt(USER_ID)
                );
            }

            return professor;
        } catch (SQLException e) {
            handleSQLException(e);
            return professor;
        }
    }
    public void updateProfessorNameSurnameById(int id, String name, String surname) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update professors set name = ?, surname = ? where id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateProfessorNameSurnameByUserId(int userId, String name, String surname) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update professors set name = ?, surname = ? where user_id = ?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteProfessorById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from professors where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteProfessorByUserId(int userId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from professors where user_id = ?")) {
            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Group CRUD
    public void createGroup(String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into groups (name) values (?);")) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<Group> readAllGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from groups;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                groups.add(new Group(
                        result.getInt(ID),
                        result.getString(NAME)
                ));
            }

            return groups;
        } catch (SQLException e) {
            handleSQLException(e);
            return groups;
        }
    }
    public Group readGroupById(int id) {
        Group group = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from groups where id = ?")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                group = new Group(
                        result.getInt(ID),
                        result.getString(NAME)
                );
            }

            return group;
        } catch (SQLException e) {
            handleSQLException(e);
            return group;
        }
    }
    public Group readGroupByName(String name) {
        Group group = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from groups where name = ?")) {
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                group = new Group(
                        result.getInt(ID),
                        result.getString(NAME)
                );
            }

            return group;
        } catch (SQLException e) {
            handleSQLException(e);
            return group;
        }
    }
    public void updateGroupNameById(int id, String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update groups set name = ? where id = ?")) {
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteGroupById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from groups where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteGroupByName(String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from groups where name = ?")) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Course CRUD
    public void createCourse(String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into courses (name) values (?);")) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<Course> readAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from courses;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                courses.add(new Course(
                        result.getInt(ID),
                        result.getString(NAME)
                ));
            }

            return courses;
        } catch (SQLException e) {
            handleSQLException(e);
            return courses;
        }
    }
    public Course readCourseById(int id) {
        Course course = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from courses where id = ?")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                course = new Course(
                        result.getInt(ID),
                        result.getString(NAME)
                );
            }

            return course;
        } catch (SQLException e) {
            handleSQLException(e);
            return course;
        }
    }
    public Course readCourseByName(String name) {
        Course course = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, name from courses where name = ?")) {
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                course = new Course(
                        result.getInt(ID),
                        result.getString(NAME)
                );
            }

            return course;
        } catch (SQLException e) {
            handleSQLException(e);
            return course;
        }
    }
    public void updateCourseNameById(int id, String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update courses set name = ? where id = ?")) {
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteCourseById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from courses where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteCourseByName(String name) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from courses where name = ?")) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Grade CRUD
    public void createGrade(int value, int studentId, int courseId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into grades (value, date, student_id, course_id) values (?, ?, ?, ?);")) {
            statement.setInt(1, value);
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            statement.setInt(3, studentId);
            statement.setInt(4, courseId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void createGrade(int value, String date, int studentId, int courseId) {
        String formatStr = "dd.MM.yyyy";

        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into grades (value, date, student_id, course_id) values (?, ?, ?, ?);")) {
            statement.setInt(1, value);
            statement.setDate(2, parseDate(date, formatStr));
            statement.setInt(3, studentId);
            statement.setInt(4, courseId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        } catch (ParseException e) {
            System.out.println("Parse error. Input date in format " + formatStr);
        }
    }
    public List<Grade> readAllGrades() {
        ArrayList<Grade> grades = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, value, date, student_id, course_id from grades;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                grades.add(new Grade(
                        result.getInt(ID),
                        result.getInt(VALUE),
                        result.getDate(DATE),
                        result.getInt(STUDENT_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return grades;
        } catch (SQLException e) {
            handleSQLException(e);
            return grades;
        }
    }
    public List<Grade> readGradesByStudentId(int studentId) {
        ArrayList<Grade> grades = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, value, date, student_id, course_id from grades where student_id = ?;")) {
            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                grades.add(new Grade(
                        result.getInt(ID),
                        result.getInt(VALUE),
                        result.getDate(DATE),
                        result.getInt(STUDENT_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return grades;
        } catch (SQLException e) {
            handleSQLException(e);
            return grades;
        }
    }
    public List<Grade> readGradesByStudentIdCourseId(int studentId, int courseId) {
        ArrayList<Grade> grades = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, value, date, student_id, course_id from grades where student_id = ? and course_id = ?;")) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                grades.add(new Grade(
                        result.getInt(ID),
                        result.getInt(VALUE),
                        result.getDate(DATE),
                        result.getInt(STUDENT_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return grades;
        } catch (SQLException e) {
            handleSQLException(e);
            return grades;
        }
    }
    public void updateGradeValueById(int id, int value) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update grades set value = ? where id = ?")) {
            statement.setInt(1, value);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateGradeDateById(int id, String date) {
        String formatStr = "dd.MM.yyyy";

        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "update grades set date = ? where id = ?")) {
            statement.setDate(1, parseDate(date, formatStr));
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        } catch (ParseException e) {
            System.out.println("Parse error. Input date in format " + formatStr);
        }
    }
    public void deleteGradeById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from grades where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Group Course CRUD
    public void createGroupCourse(int groupId, int courseId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into group_courses (group_id, course_id) values (?, ?)")) {
            statement.setInt(1, groupId);
            statement.setInt(2, courseId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<GroupCourse> readAllGroupCourses() {
        ArrayList<GroupCourse> groupCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, group_id, course_id from group_courses;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                groupCourses.add(new GroupCourse(
                        result.getInt(ID),
                        result.getInt(GROUP_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return groupCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return groupCourses;
        }
    }
    public List<GroupCourse> readGroupCoursesByGroupId(int groupId) {
        ArrayList<GroupCourse> groupCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, group_id, course_id from group_courses where group_id = ?;")) {
            statement.setInt(1, groupId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                groupCourses.add(new GroupCourse(
                        result.getInt(ID),
                        result.getInt(GROUP_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return groupCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return groupCourses;
        }
    }
    public List<GroupCourse> readGroupCoursesByCourseId(int courseId) {
        ArrayList<GroupCourse> groupCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, group_id, course_id from group_courses where course_id = ?;")) {
            statement.setInt(1, courseId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                groupCourses.add(new GroupCourse(
                        result.getInt(ID),
                        result.getInt(GROUP_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return groupCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return groupCourses;
        }
    }
    public void deleteGroupCourseById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from group_courses where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Professor Course CRUD
    public void createProfessorCourse(int professorId, int courseId) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "insert into professor_courses (professor_id, course_id) values (?, ?)")) {
            statement.setInt(1, professorId);
            statement.setInt(2, courseId);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<ProfessorCourse> readAllProfessorCourses() {
        ArrayList<ProfessorCourse> professorCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, professor_id, course_id from professor_courses;")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                professorCourses.add(new ProfessorCourse(
                        result.getInt(ID),
                        result.getInt(PROFESSOR_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return professorCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return professorCourses;
        }
    }
    public List<ProfessorCourse> readProfessorCoursesByProfessorId(int professorId) {
        ArrayList<ProfessorCourse> professorCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, professor_id, course_id from professor_courses where professor_id = ?;")) {
            statement.setInt(1, professorId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                professorCourses.add(new ProfessorCourse(
                        result.getInt(ID),
                        result.getInt(PROFESSOR_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return professorCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return professorCourses;
        }
    }
    public List<ProfessorCourse> readProfessorCoursesByCourseId(int courseId) {
        ArrayList<ProfessorCourse> professorCourses = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "select id, professor_id, course_id from professor_courses where course_id = ?;")) {
            statement.setInt(1, courseId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                professorCourses.add(new ProfessorCourse(
                        result.getInt(ID),
                        result.getInt(GROUP_ID),
                        result.getInt(COURSE_ID)
                ));
            }

            return professorCourses;
        } catch (SQLException e) {
            handleSQLException(e);
            return professorCourses;
        }
    }
    public void deleteProfessorCourseById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement(
                "delete from professor_courses where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private static java.sql.Date parseDate(String date, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        java.util.Date utilDate = format.parse(date);
        return new java.sql.Date(utilDate.getTime());
    }
    @Override
    public void close() throws SQLException {
        dbHelper.close();
    }
}
