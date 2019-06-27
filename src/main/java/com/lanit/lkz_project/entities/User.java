package com.lanit.lkz_project.entities;


import javax.persistence.*;

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

    public User() {

    }

    public User(Organization organization, String firstName, String lastName) {
        this.organization = organization;
        this.firstName = firstName;
        this.lastName = lastName;
    }


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
