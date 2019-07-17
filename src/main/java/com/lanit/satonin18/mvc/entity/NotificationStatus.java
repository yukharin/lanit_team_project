package com.lanit.satonin18.mvc.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notification_statuses")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class NotificationStatus  implements Serializable {
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
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Override
    public String toString() {
        return "NotificationStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
