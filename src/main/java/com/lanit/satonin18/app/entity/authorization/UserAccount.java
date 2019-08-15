package com.lanit.satonin18.app.entity.authorization;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    @Id
    @Column(name = "id_user", nullable = false)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
//    private User user;

    //уникалбное поле //+уникальный индекс
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Basic
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userAccount")
    private List<Authority> authorities;

    @Override
    public String toString() {
        return "UserAccount{" +
//                "id=" + id +
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
