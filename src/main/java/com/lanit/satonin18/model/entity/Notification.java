package com.lanit.satonin18.model.entity;

import lombok.*;

import javax.persistence.*;

//@Component //for CHECK SpringMVC without Hibernate
@Entity
@Table(name = "notifications")

@Data //get and set
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification { //Serializable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_org", referencedColumnName = "id", nullable = false)
    private Organization organization;

    //-------------------------
}
