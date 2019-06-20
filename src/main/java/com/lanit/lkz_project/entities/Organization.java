package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "id")
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


    public Organization()
    {

    }

    public Organization(String name, boolean orgType, Organization govOrganization, List<Notification> notifications, List<User> users) {
        this.name = name;
        this.orgType = orgType;
        this.govOrganization = govOrganization;
        this.notifications = notifications;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOrgType() {
        return orgType;
    }

    public void setOrgType(boolean orgType) {
        this.orgType = orgType;
    }

    public Organization getGovOrganization() {
        return govOrganization;
    }

    public void setGovOrganization(Organization govOrganization) {
        this.govOrganization = govOrganization;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orgType=" + orgType +
                ", govOrganization=" + govOrganization +
                ", notifications=" + notifications +
                ", users=" + users +
                '}';
    }
}
