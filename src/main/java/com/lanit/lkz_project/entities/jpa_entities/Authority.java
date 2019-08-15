package com.lanit.lkz_project.entities.jpa_entities;

import com.lanit.lkz_project.entities.enums.AuthorityValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(exclude = {"id", "users"})
@ToString(exclude = {"id", "users"})
public class Authority implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AuthorityValue role;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public Authority() {

    }

    public Authority(AuthorityValue role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }


}

