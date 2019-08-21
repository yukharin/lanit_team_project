package com.lanit.satonin18.app.entity.authorization;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "user_accounts_authorities")

//@Data
//@ToString //can be loop
@EqualsAndHashCode
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
//    @Enumerated(EnumType.STRING)
//    private Role authority; // implements GrantedAuthority //String getAuthority();
    private String authority;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
//                ", userAccount=" + userAccount +
                ", authority='" + authority + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
