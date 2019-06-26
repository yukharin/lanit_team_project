package com.lanit.satonin18.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Component //for CHECK SpringMVC without Hibernate
@Entity
//@Embeddable
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    protected Organization organization;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", organization=" + organization +
                '}';
    }
}
