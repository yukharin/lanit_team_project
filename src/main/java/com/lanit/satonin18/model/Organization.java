package com.lanit.satonin18.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    //can be trueER need added @Converter FROM(in MySQL type = TINYINT(1)) IN boolean
    @Column(name = "org_type")
    private boolean government;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gos_org")
    private Organization government_org;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)//, cascade = CascadeType.ALL)
    protected List<User> users = new ArrayList<User>();// = new ArrayList<>();

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", government=" + government +
                ", government_org=" + government_org +
// loop         ", users=" + users +
                '}';
    }

}
