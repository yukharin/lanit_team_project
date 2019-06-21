package com.lanit.satonin18.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization { //Serializable

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    //TODO in MySQL type = TINYINT(1)
    @Column(name = "org_type")
    private boolean orgType;


    public Organization(){
    }

    public Organization(String name, boolean orgType) {
        this.name = name;
        this.orgType = orgType;
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

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orgType=" + orgType +
                '}';
    }
}
