package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "organizations")

//@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Organization implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    //can be trueER need added @Converter FROM(in MySQL type = TINYINT(1)) IN boolean
    @Basic
    @Column(name = "org_type", nullable = false)
    private boolean government;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "org")//, fetch = FetchType.EAGER)//, cascade = CascadeType.ALL))
    private/*protected*/ List<User> users;// = new ArrayList<User>();

    //TODO: CAN BE ADDED AUTO TRUE SORT @ORDER BY DATA_RESPONSE@ or comporator in other level abstract ???
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")//, fetch = FetchType.EAGER)//, cascade = CascadeType.ALL)//FetchType.LAZY)//
    private/*protected*/ List<Notification> notifications;// = new ArrayList<Notification>();

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id;
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

    public boolean isGovernment() {
        return government;
    }

    public void setGovernment(boolean government) {
        this.government = government;
    }

    public Organization getGovernment_org() {
        return government_org;
    }

    public void setGovernment_org(Organization government_org) {
        this.government_org = government_org;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
