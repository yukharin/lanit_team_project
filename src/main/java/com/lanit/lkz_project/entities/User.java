package com.lanit.lkz_project.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organization"})
@ToString(exclude = "organization")
@Entity
@Table(name = "users")
@JsonIgnoreProperties("organization")
public class User implements Serializable {

    private static int counter = 0;

    {
        counter++;
        System.err.println("Users: " + counter);
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    private Organization organization;

    @NotBlank
    @Size(min = 3, max = 45, message = "First name must be of length ranging from 3 to 45.")
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$",
            message = "First name shouldn't have any special characters other than underscore.")
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 45, message = "Last name must be of length ranging from 3 to 45.")
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$",
            message = "Last name shouldn't have any special characters other than underscore.")
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 45, message = "Login must be of length ranging from 3 to 45.")
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$",
            message = "Login shouldn't have any special characters other than underscore.")
    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @NotBlank
    @Size(min = 7, max = 45,
            message = "Password should have at least 7 characters " +
                    "and be no longer than 45 characters")
    @Pattern(regexp = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$",
            message = "Password must contain at least one letter, " +
                    "at least one number, and be longer than six charaters.")
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @NotNull
    @Column(name = "role")
    private Role role;

}
