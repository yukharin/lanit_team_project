package com.lanit.satonin18.app.entity.authorization;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts_authorities")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Authority {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_account", referencedColumnName = "id_user", nullable = false)
    private Account account;

    @Basic
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
//                ", account=" + account +
                ", authority='" + authority + '\'' +
                '}';
    }
}
