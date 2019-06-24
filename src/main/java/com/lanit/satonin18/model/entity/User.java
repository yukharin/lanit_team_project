package com.lanit.satonin18.model.entity;

import lombok.*;
import javax.persistence.*;

//@Component //for CHECK SpringMVC without Hibernate
@Entity
@Table(name = "users")

//@Data //get and set
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class User { //Serializable
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;

    @ManyToOne
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    private Organization organization;

    //-------------------------

    public User() {
    }

    public User(String first_name, String last_name, Organization organization) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", organization=" + organization +
                '}';
    }
}
