package models;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @SequenceGenerator(name = "grades_seq",
            sequenceName = "grades_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "grades_seq")
    private int id;
    private int value;
    @Basic
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public Grade() {}
    public Grade(int value) {
        this.value = value;
        this.date = new Date(System.currentTimeMillis());
    }
    public Grade(int value, String date) {
        this.value = value;
        try {
            this.date = parseDate(date);
        } catch (ParseException e) {
            this.date = new Date(System.currentTimeMillis());
            System.out.println("Date parse exception. Current date has been set instead.");
        }

    }
    public Grade(int value, Student student, Course course) {
        this.value = value;
        this.date = new Date(System.currentTimeMillis());
        this.student = student;
        this.course = course;
    }
    public Grade(int value, String date, Student student, Course course) {
        this.value = value;
        try {
            this.date = parseDate(date);
        } catch (ParseException e) {
            this.date = new Date(System.currentTimeMillis());
            System.out.println("Date parse exception. Current date has been set instead.");
        }
        this.student = student;
        this.course = course;
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
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", value=" + value +
                ", sqlDate=" + date +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

    public static Date parseDate(String date) throws ParseException {
        String formatStr = "dd.MM.yyyy";
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        java.util.Date utilDate = format.parse(date);
        return new Date(utilDate.getTime());
    }
    public static Date parseDate(String date, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        java.util.Date utilDate = format.parse(date);
        return new Date(utilDate.getTime());
    }
}
