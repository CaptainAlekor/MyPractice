package entities;

public class Course {
    private final int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("Course: { id: %d, name: %s }", id, name);
    }
}
