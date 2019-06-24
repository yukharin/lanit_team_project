package com.lanit.satonin18.model.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;

//@Component //for CHECK SpringMVC without Hibernate
@Entity
@Table(name = "organizations")

//@Data //get and set
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Organization { //Serializable
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

    //@Access(AccessType.PROPERTY)
    //@Column
    //@ElementCollection(targetClass=User.class)

//    @OneToMany(mappedBy = "organization")
//    private List<User> users ;

    //@OneToMany(mappedBy = "organization")
    //private List<Notification> notifications = new ArrayList<>();

    //-----------------------------------------

    public Organization() {
    }

//    public Organization(String name, boolean government, Organization government_org, List<User> users) {
//        this.name = name;
//        this.government = government;
//        this.government_org = government_org;
//        this.users = users;
//    }

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


//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", government=" + government +
                ", government_org=" + government_org +
                '}';
    }
}
