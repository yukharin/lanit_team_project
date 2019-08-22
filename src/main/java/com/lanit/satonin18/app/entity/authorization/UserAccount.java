package com.lanit.satonin18.app.entity.authorization;

import com.lanit.satonin18.app.entity.User;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_accounts")

//@Data
@ToString //can be loop
@EqualsAndHashCode(exclude = {"authorities"})
//@NoArgsConstructor
//@AllArgsConstructor
public class UserAccount
        implements UserDetails,
        Serializable {

    @Id
    @Column(name = "id_user", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    //уникалбное поле //+уникальный индекс
//    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "username", nullable = false, length = 50)
    private String username;

//    @NotBlank //HEX
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

//    @NotEmpty
    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "userAccount")
    private List<Authority> authorities;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

//    public enum AuthorityValue {EMPLOYEE, AUTHORITY, ADMIN}
//    public boolean hasAuthority(AuthorityValue authority) {
//        for (Authority temp : authorities) {
//            if (temp.getAuthority().equals(authority.toString())) {
//                return true;
//            }
//        }
//        return false;
//    }


//    @Override
//    public String toString() {
//        return "UserAccount{" +
//                "id=" + id +
//                ", user=" + user +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", enabled=" + enabled +
//                ", authorities=" + authorities +
//                ", accountNonExpired=" + accountNonExpired +
//                ", accountNonLocked=" + accountNonLocked +
//                ", credentialsNonExpired=" + credentialsNonExpired +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAccount that = (UserAccount) o;
//        return id == that.id &&
//                enabled == that.enabled &&
//                accountNonExpired == that.accountNonExpired &&
//                accountNonLocked == that.accountNonLocked &&
//                credentialsNonExpired == that.credentialsNonExpired &&
//                Objects.equals(user, that.user) &&
//                Objects.equals(username, that.username) &&
//                Objects.equals(password, that.password) &&
//                Objects.equals(authorities, that.authorities)
//                ;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, user, username, password, enabled, authorities, accountNonExpired, accountNonLocked, credentialsNonExpired);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
