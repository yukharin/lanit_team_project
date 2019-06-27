package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "org_type", nullable = false)
    private boolean orgType;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization govOrganization;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")
    private transient List<Notification> notifications;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")
    private transient List<User> users;


    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orgType=" + orgType +
                ", govOrganization=" + govOrganization +
                '}';
    }
}
