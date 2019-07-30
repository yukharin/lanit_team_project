package com.lanit.satonin18.app.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "organizations")

@Data //get and set
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

    @Basic
    @Column(name = "org_type", nullable = false)
    private boolean government;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")//, fetch = FetchType.EAGER)
    private List<User> users;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")//, fetch = FetchType.EAGER)
    private List<Notification> notifications;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", IS_government?=" + government +
                ", government_org = " + government_org +
                ", users=" + users +
                ", notifications=" + notifications +
                '}';
    }
}
