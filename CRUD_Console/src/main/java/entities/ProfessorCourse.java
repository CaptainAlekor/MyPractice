package entities;

public class ProfessorCourse {
    private final int id;
    private final int professorId;
    private final int courseId;

    public ProfessorCourse(int id, int professorId, int courseId) {
        this.id = id;
        this.professorId = professorId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public int getProfessorId() {
        return professorId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return String.format("Professor Course: { id: %d, professor_id: %d, course_id: %d }",
                id,
                professorId,
                courseId);
    }
}
