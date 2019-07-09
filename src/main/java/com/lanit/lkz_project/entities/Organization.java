package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "organizations")
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

    @OneToMany(mappedBy = "organization")
    private List<User> users;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private List<Notification> notifications;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", government=" + government +
                ", government_org=" + government_org +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id == that.id &&
                government == that.government &&
                Objects.equals(name, that.name) &&
                Objects.equals(government_org, that.government_org) &&
                Objects.equals(users, that.users) &&
                Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, government, government_org, users, notifications);
    }
}
