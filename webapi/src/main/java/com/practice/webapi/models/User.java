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
    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Person person;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, Person person) {
        this.email = email;
        this.password = password;
        this.person = person;
        person.setUser(this);
    }
}
