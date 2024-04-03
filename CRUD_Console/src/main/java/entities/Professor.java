package entities;

public class Professor {
    private final int id;
    private String name;
    private String surname;
    private final int userId;

    public Professor(int id, String name, String surname, int userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return String.format("Professor: {id: %d, name: %s, surname: %s, user_id: %d }",
                id,
                name,
                surname,
                userId);
    }
}
