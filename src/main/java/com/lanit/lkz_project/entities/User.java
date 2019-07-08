package com.lanit.lkz_project.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", organization=" + organization +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}