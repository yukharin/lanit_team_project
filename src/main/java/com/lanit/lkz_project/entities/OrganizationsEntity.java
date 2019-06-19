package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "lanit", catalog = "")
public class OrganizationsEntity {
    private int id;
    private String name;
    private byte orgType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "org_type")
    public byte getOrgType() {
        return orgType;
    }

    public void setOrgType(byte orgType) {
        this.orgType = orgType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationsEntity that = (OrganizationsEntity) o;
        return id == that.id &&
                orgType == that.orgType &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, orgType);
    }
}
