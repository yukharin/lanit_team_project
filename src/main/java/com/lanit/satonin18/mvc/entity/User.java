package com.lanit.satonin18.mvc.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "enhanced-sequence", /* Стратегия применения расширенной последовательности*/
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_name", /* Имя последовательности*/
                            value = "JPWH_SEQUENCE"
                    ),
                    @org.hibernate.annotations.Parameter(
                            name = "initial_value", /* Начальное значение*/
                            value = "1000"
                    )
            })
    private int id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    private/*protected*/ Organization organization;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
//                ", organization=" + organization +
                '}';
    }
}
