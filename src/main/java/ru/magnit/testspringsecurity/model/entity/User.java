package ru.magnit.testspringsecurity.model.entity;

import lombok.Data;
import ru.magnit.testspringsecurity.model.Role;
import ru.magnit.testspringsecurity.model.Status;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_test1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}