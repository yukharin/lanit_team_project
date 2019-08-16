package com.lanit.satonin18.app.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 45)
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
//                ", organization=" + organization +
                '}';
    }

    public User() {
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuu");
    }

    public User(String firstName, String lastName, Organization organization) {
        super();

        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }
}
