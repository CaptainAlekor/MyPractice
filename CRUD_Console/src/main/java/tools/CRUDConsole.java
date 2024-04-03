package tools;

import entities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CRUDConsole {
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
    private static final String ERR_UNKNOWN_OPTION = "Unknown option";
    private static final Scanner in = new Scanner(System.in);
    private CRUDConsole(){}
    public static void start() {
        while (true) {
            System.out.println("""
                    -----------------------------
                    Choose a table:
                    1 - Users
                    2 - Students
                    3 - Professors
                    4 - Groups
                    5 - Courses
                    6 - Grades
                    7 - GroupCourses
                    8 - ProfessorCourses
                    
                    0 - Exit
                    """);

            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    usersScreen();
                    break;
                case 2:
                    studentsScreen();
                    break;
                case 3:
                    professorsScreen();
                    break;
                case 4:
                    groupsScreen();
                    break;
                case 5:
                    coursesScreen();
                    break;
                case 6:
                    gradesScreen();
                    break;
                case 7:
                    groupCoursesScreen();
                    break;
                case 8:
                    professorCoursesScreen();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ERR_UNKNOWN_OPTION);
            }
        }
    }
    private static void usersScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE user
                        2 - READ all users
                        3 - READ user by id
                        4 - READ user by email
                        5 - UPDATE user by id
                        6 - UPDATE user email by id
                        7 - UPDATE user password by id
                        8 - DELETE user by id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                switch (choice) {
                    case 1:
                        crud.createUser(
                                getValidString(EMAIL),
                                getValidString(PASSWORD)
                        );
                        break;
                    case 2:
                        List<User> users = crud.readAllUsers();
                        for (User user : users) {
                            System.out.println(user);
                        }
                        break;
                    case 3:
                        System.out.println(crud.readUserById(getValidInt(ID)));
                        break;
                    case 4:
                        System.out.println(crud.readUserByEmail(getValidString(EMAIL)));
                        break;
                    case 5:
                        crud.updateUserById(
                                getValidInt(ID),
                                getValidString(EMAIL),
                                getValidString(PASSWORD)
                        );
                        break;
                    case 6:
                        crud.updateUserEmailById(
                                getValidInt(ID),
                                getValidString(EMAIL)
                        );
                        break;
                    case 7:
                        crud.updateUserPasswordById(
                                getValidInt(ID),
                                getValidString(PASSWORD)
                        );
                        break;
                    case 8:
                        crud.deleteUserById(getValidInt(ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void professorsScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE professor
                        2 - READ all professors
                        3 - READ professor by id
                        4 - READ professor by name and surname
                        5 - READ professor by user_id
                        6 - UPDATE professor name and surname by id
                        7 - UPDATE professor name and surname by user_id
                        8 - DELETE professor by id
                        9 - DELETE professor by user_id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                switch (choice) {
                    case 1:
                        crud.createProfessor(
                                getValidString(NAME),
                                getValidString(SURNAME),
                                getValidInt(USER_ID)
                        );
                        break;
                    case 2:
                        List<Professor> professors = crud.readAllProfessors();
                        for (Professor professor : professors) {
                            System.out.println(professor);
                        }
                        break;
                    case 3:
                        System.out.println(crud.readProfessorById(getValidInt(ID)));
                        break;
                    case 4:
                        System.out.println(crud.readProfessorByNameSurname(
                                getValidString(NAME),
                                getValidString(SURNAME)
                        ));
                        break;
                    case 5:
                        System.out.println(crud.readProfessorByUserId(getValidInt(USER_ID)));
                        break;
                    case 6:
                        crud.updateProfessorNameSurnameById(
                                getValidInt(ID),
                                getValidString(NAME),
                                getValidString(SURNAME));
                        break;
                    case 7:
                        crud.updateProfessorNameSurnameByUserId(
                                getValidInt(USER_ID),
                                getValidString(NAME),
                                getValidString(SURNAME));
                        break;
                    case 8:
                        crud.deleteProfessorById(getValidInt(ID));
                        break;
                    case 9:
                        crud.deleteProfessorByUserId(getValidInt(USER_ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void groupsScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE group
                        2 - READ all groups
                        3 - READ group by id
                        4 - READ group by name
                        5 - UPDATE group name by id
                        6 - DELETE group by id
                        7 - DELETE group by name
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                switch (choice) {
                    case 1:
                        crud.createGroup(getValidString(NAME));
                        break;
                    case 2:
                        List<Group> groups = crud.readAllGroups();
                        for (Group group : groups) {
                            System.out.println(group);
                        }
                        break;
                    case 3:
                        System.out.println(crud.readGroupById(getValidInt(ID)));
                        break;
                    case 4:
                        System.out.println(crud.readGroupByName(getValidString(NAME)));
                        break;
                    case 5:
                        crud.updateGroupNameById(
                                getValidInt(ID),
                                getValidString(NAME)
                        );
                        break;
                    case 6:
                        crud.deleteGroupById(getValidInt(ID));
                        break;
                    case 7:
                        crud.deleteGroupByName(getValidString(NAME));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void coursesScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE course
                        2 - READ all courses
                        3 - READ course by id
                        4 - READ course by name
                        5 - UPDATE course name by id
                        6 - DELETE course by id
                        7 - DELETE course by name
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                switch (choice) {
                    case 1:
                        crud.createCourse(getValidString(NAME));
                        break;
                    case 2:
                        List<Course> courses = crud.readAllCourses();
                        for (Course course : courses) {
                            System.out.println(course);
                        }
                        break;
                    case 3:
                        System.out.println(crud.readCourseById(getValidInt(ID)));
                        break;
                    case 4:
                        System.out.println(crud.readCourseByName(getValidString(NAME)));
                        break;
                    case 5:
                        crud.updateCourseNameById(
                                getValidInt(ID),
                                getValidString(NAME)
                        );
                        break;
                    case 6:
                        crud.deleteCourseById(getValidInt(ID));
                        break;
                    case 7:
                        crud.deleteCourseByName(getValidString(NAME));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void studentsScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE student
                        2 - READ all students
                        3 - READ students by group_id
                        4 - READ student by id
                        5 - READ student by name and surname
                        6 - READ student by user_id
                        7 - UPDATE student by id
                        8 - UPDATE student by user_id
                        9 - UPDATE student name and surname by id
                        10 - UPDATE student name and surname by user_id
                        11 - UPDATE student group_id by id
                        12 - UPDATE student group_id by user_id
                        13 - DELETE student by id
                        14 - DELETE student by user_id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                List<Student> students;

                switch (choice) {
                    case 1:
                        crud.createStudent(
                                getValidString(NAME),
                                getValidString(SURNAME),
                                getValidInt(GROUP_ID),
                                getValidInt(USER_ID)
                        );
                        break;
                    case 2:
                        students = crud.readAllStudents();
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        break;
                    case 3:
                        students = crud.readStudentsByGroupId(getValidInt(GROUP_ID));
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        break;
                    case 4:
                        System.out.println(crud.readStudentById(getValidInt(ID)));
                        break;
                    case 5:
                        System.out.println(crud.readStudentByNameSurname(
                                getValidString(NAME),
                                getValidString(SURNAME)
                        ));
                        break;
                    case 6:
                        System.out.println(crud.readStudentByUserId(getValidInt(USER_ID)));
                        break;
                    case 7:
                        crud.updateStudentById(
                                getValidInt(ID),
                                getValidString(NAME),
                                getValidString(SURNAME),
                                getValidInt(GROUP_ID)
                        );
                        break;
                    case 8:
                        crud.updateStudentByUserId(
                                getValidInt(USER_ID),
                                getValidString(NAME),
                                getValidString(SURNAME),
                                getValidInt(GROUP_ID)
                        );
                        break;
                    case 9:
                        crud.updateStudentNameSurnameById(
                                getValidInt(ID),
                                getValidString(NAME),
                                getValidString(SURNAME)
                        );
                        break;
                    case 10:
                        crud.updateStudentNameSurnameByUserId(
                                getValidInt(USER_ID),
                                getValidString(NAME),
                                getValidString(SURNAME)
                        );
                        break;
                    case 11:
                        crud.updateStudentGroupIdById(
                                getValidInt(ID),
                                getValidInt(GROUP_ID)
                        );
                        break;
                    case 12:
                        crud.updateStudentGroupIdByUserId(
                                getValidInt(USER_ID),
                                getValidInt(GROUP_ID)
                        );
                        break;
                    case 13:
                        crud.deleteStudentById(getValidInt(ID));
                        break;
                    case 14:
                        crud.deleteStudentByUserId(getValidInt(USER_ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void gradesScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE grade
                        2 - CREATE grade with specific date
                        3 - READ all grades
                        4 - READ grades by student_id
                        5 - READ grades by student_id and course_id
                        6 - UPDATE grade value by id
                        7 - UPDATE grade date by id
                        8 - DELETE grade by id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                List<Grade> grades;
                switch (choice) {
                    case 1:
                        crud.createGrade(
                                getValidInt(VALUE),
                                getValidInt(STUDENT_ID),
                                getValidInt(COURSE_ID)
                        );
                        break;
                    case 2:
                        crud.createGrade(
                                getValidInt(VALUE),
                                getValidString(DATE),
                                getValidInt(STUDENT_ID),
                                getValidInt(COURSE_ID)
                        );
                        break;
                    case 3:
                        grades = crud.readAllGrades();
                        for (Grade grade : grades) {
                            System.out.println(grade);
                        }
                        break;
                    case 4:
                        grades = crud.readGradesByStudentId(getValidInt(STUDENT_ID));
                        for (Grade grade : grades) {
                            System.out.println(grade);
                        }
                        break;
                    case 5:
                        grades = crud.readGradesByStudentIdCourseId(
                                getValidInt(STUDENT_ID),
                                getValidInt(COURSE_ID)
                        );
                        for (Grade grade : grades) {
                            System.out.println(grade);
                        }
                        break;
                    case 6:
                        crud.updateGradeValueById(
                                getValidInt(ID),
                                getValidInt(VALUE)
                        );
                        break;
                    case 7:
                        crud.updateGradeDateById(
                                getValidInt(ID),
                                getValidString(DATE)
                        );
                        break;
                    case 8:
                        crud.deleteGradeById(getValidInt(ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void groupCoursesScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE group course
                        2 - READ all group courses
                        3 - READ group courses by group_id
                        4 - READ group courses by course_id
                        5 - DELETE group course by id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                List<GroupCourse> groupCourses;

                switch (choice) {
                    case 1:
                        crud.createGroupCourse(
                                getValidInt(GROUP_ID),
                                getValidInt(COURSE_ID)
                        );
                        break;
                    case 2:
                        groupCourses = crud.readAllGroupCourses();
                        for (GroupCourse groupCourse : groupCourses) {
                            System.out.println(groupCourse);
                        }
                        break;
                    case 3:
                        groupCourses = crud.readGroupCoursesByGroupId(getValidInt(GROUP_ID));
                        for (GroupCourse groupCourse : groupCourses) {
                            System.out.println(groupCourse);
                        }
                        break;
                    case 4:
                        groupCourses = crud.readGroupCoursesByCourseId(getValidInt(COURSE_ID));
                        for (GroupCourse groupCourse : groupCourses) {
                            System.out.println(groupCourse);
                        }
                        break;
                    case 5:
                        crud.deleteGroupCourseById(getValidInt(ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
    private static void professorCoursesScreen() {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            while (true) {
                System.out.println("""
                        -----------------------------
                        Choose an action:
                        1 - CREATE professor course
                        2 - READ all professor courses
                        3 - READ professor courses by professor_id
                        4 - READ professor courses by course_id
                        5 - DELETE professor course by id
                                        
                        0 - Exit to entity choice
                        """);

                int choice = getValidChoice();

                List<ProfessorCourse> professorCourses;

                switch (choice) {
                    case 1:
                        crud.createProfessorCourse(
                                getValidInt(PROFESSOR_ID),
                                getValidInt(COURSE_ID)
                        );
                        break;
                    case 2:
                        professorCourses = crud.readAllProfessorCourses();
                        for (ProfessorCourse professorCourse : professorCourses) {
                            System.out.println(professorCourse);
                        }
                        break;
                    case 3:
                        professorCourses = crud.readProfessorCoursesByProfessorId(getValidInt(PROFESSOR_ID));
                        for (ProfessorCourse professorCourse : professorCourses) {
                            System.out.println(professorCourse);
                        }
                        break;
                    case 4:
                        professorCourses = crud.readProfessorCoursesByCourseId(getValidInt(COURSE_ID));
                        for (ProfessorCourse professorCourse : professorCourses) {
                            System.out.println(professorCourse);
                        }
                        break;
                    case 5:
                        crud.deleteProfessorCourseById(getValidInt(ID));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(ERR_UNKNOWN_OPTION);
                }
            }
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }

    private static int getValidInt(String valueName) {
        int returnValue;
        while (true) {

            System.out.printf("""
                    -----------------------------
                    Enter %s
                    """, valueName);
            try {
                returnValue = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter valid integer value");
                continue;
            }
            return returnValue;
        }
    }
    private static String getValidString(String valueName) {
        String returnValue;
        while (true) {
            System.out.printf("""
                    -----------------------------
                    Enter %s
                    """, valueName);
            returnValue = in.nextLine();
            if (!returnValue.isBlank()) {
                return returnValue;
            } else {
                System.out.println("Enter valid string value");
            }
        }
    }
    private static int getValidChoice() {
        int choice;
        try {
            choice = Integer.parseInt(in.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
