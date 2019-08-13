package com.lanit.lkz_project.entities.jpa_entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lanit.lkz_project.entities.enums.Role;
import com.lanit.lkz_project.entities.validation_groups.UserValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organization"})
@ToString(exclude = "organization")
@Entity
@Table(name = "users")
@JsonIgnoreProperties("organization")
public class User implements Serializable, UserDetails {

    private static int counter = 0;

    {
        counter++;
        System.err.println("Users: " + counter);
    }


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(groups = UserValidationGroup.class)
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Organization organization;

    @NotBlank(groups = UserValidationGroup.class)
    @Size(min = 3, max = 45, groups = UserValidationGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$", groups = UserValidationGroup.class)
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotBlank(groups = UserValidationGroup.class)
    @Size(min = 3, max = 45, groups = UserValidationGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$", groups = UserValidationGroup.class)
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;


    @NotBlank(groups = UserValidationGroup.class)
    @Size(min = 3, max = 45, groups = UserValidationGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$",
            groups = UserValidationGroup.class)
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @NotBlank(groups = UserValidationGroup.class)
    @Size(min = 7, max = 60, groups = UserValidationGroup.class)
    @Pattern(regexp = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$", groups = UserValidationGroup.class)
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @PastOrPresent
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @NotNull
    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(this.role);
        return roles;
    }
}
