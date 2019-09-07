package com.lanit.lkz_project.entities.jpa_entities;


import com.lanit.lkz_project.entities.enums.AuthorityValue;
import com.lanit.lkz_project.entities.validation_groups.UserValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organization"})
@ToString(exclude = "organization")
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(groups = UserValidationGroup.class)
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Organization organization;

    @NotBlank
    @Size(min = 3, max = 45)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$")
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 45)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$")
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;


    @NotBlank
    @Size(min = 3, max = 45)
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{2,44}$")
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @NotBlank
    @Size(min = 8, max = 60)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Пароль должен иметь как минимум одну заглавную букву, одну строчную и одну цифру")
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @PastOrPresent
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_role", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    public boolean hasAuthority(AuthorityValue authority) {
        for (Authority temp : authorities) {
            if (temp.getAuthority().equals(authority.toString())) {
                return true;
            }
        }
        return false;
    }
}
