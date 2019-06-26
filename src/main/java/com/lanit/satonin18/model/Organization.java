package com.lanit.satonin18.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    //can be trueER need added @Converter FROM(in MySQL type = TINYINT(1)) IN boolean
    @Basic
    @Column(name = "org_type", nullable = false)
    private boolean government;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)//, cascade = CascadeType.ALL))
    protected List<User> users = new ArrayList<User>();

//    @OneToMany(mappedBy = "organization")
//    private Collection<Notification> notifications;// = new ArrayList<>();

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", government=" + government +
                ", government_org=" + government_org +
//                ", users=" + users +
//                ", notifications=" + notifications +
                '}';
    }
}
