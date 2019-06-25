package com.lanit.satonin18.model.entity;

import lombok.*;
import javax.persistence.*;

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
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;

    @ManyToOne(fetch = FetchType.EAGER)//, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    protected Organization organization;

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
