package com.lanit.satonin18.app.entity.authorization;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user_accounts_authorities")

//@Data
//@ToString //can be loop
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Authority
        implements GrantedAuthority {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_user", nullable = false)
    private UserAccount userAccount;

    @NotBlank
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
//                ", userAccount=" + userAccount +
                ", authority='" + authority + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return id == authority1.id &&
                Objects.equals(userAccount, authority1.userAccount) &&
                Objects.equals(authority, authority1.authority);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, userAccount, authority);
    }

    public int getId() {
        return id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
