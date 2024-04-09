package com;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(name = "users_seq",
            sequenceName = "users_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_seq")
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Person person;

    public User() {}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", person=" + person +
                '}';
    }
}
