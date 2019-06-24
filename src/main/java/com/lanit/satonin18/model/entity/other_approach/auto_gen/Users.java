package com.lanit.satonin18.model.entity.other_approach.auto_gen;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    private int id;
    private Organizations organizationsByIdOrg;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    public Organizations getOrganizationsByIdOrg() {
        return organizationsByIdOrg;
    }

    public void setOrganizationsByIdOrg(Organizations organizationsByIdOrg) {
        this.organizationsByIdOrg = organizationsByIdOrg;
    }
}
