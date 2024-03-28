package entities;

import java.sql.Date;

public class Grade {
    private final int id;
    private int value;
    private Date date;
    private final int studentId;
    private final int courseId;

    public Grade(int id, int value, Date date, int studentId, int courseId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}
