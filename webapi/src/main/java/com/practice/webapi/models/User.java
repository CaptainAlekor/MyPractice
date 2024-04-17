package com.practice.webapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq")
    private int id;
    @Setter
    @Column(unique = true)
    private String email;
    @Setter
    private String password;
    @Setter
    private String role;
    @Setter
    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Person person;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(String email, String password, String role, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
        this.role = role;
        person.setUser(this);
    }
}
