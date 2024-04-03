package entities;

public class Student {
    private final int id;
    private String name;
    private String surname;
    private int groupId;
    private final int userId;


    public Student(int id, String name, String surname, int groupId, int userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.groupId = groupId;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return String.format("Student: {id: %d, name: %s, surname: %s, group_id: %d, user_id: %d }",
                id,
                name,
                surname,
                groupId,
                userId);
    }
}
