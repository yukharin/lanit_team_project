package com.lanit.satonin18.app.entity.authorization;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")

@Data
//@ToString //can be loop
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class UserAccount implements Serializable {

    @Id
    @Column(name = "id_user", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    //уникалбное поле //+уникальный индекс
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotBlank //HEX
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @NotEmpty
    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userAccount")
    private List<Authority> authorities;

    @Override
    public String toString() {
        return "UserAccount{" +
                ", id=" + id +
//                ", user=" + user +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }

    public UserAccount() {
        System.out.println("44444444444444444444444444444");
    }

//    public UserAccount(int id, String username, String password, boolean enabled, Set<Authority> authorities) {
//        this();
//
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.authorities = authorities;
//    }
}
