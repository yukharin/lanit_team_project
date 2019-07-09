package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Basic
    @Column(name = "org_type", nullable = false)
    private boolean government;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @OneToMany(mappedBy = "org")
    private Set<User> users;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private Set<Notification> notifications;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id;
    }


}
