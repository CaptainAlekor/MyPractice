package entities;

public class GroupCourse {
    private final int id;
    private final int groupId;
    private final int courseId;

    public GroupCourse(int id, int groupId, int courseId) {
        this.id = id;
        this.groupId = groupId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return String.format("Group Course: { id: %d, group_id: %d, course_id:%d }",
                id,
                groupId,
                courseId);
    }
}
