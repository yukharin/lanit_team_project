package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "org_type")
    private boolean orgType;

    @ManyToOne
    private Organization govOrganization;

    @OneToMany
    private List<Notification> notifications;

    @OneToMany
    private List<User> users;


}
