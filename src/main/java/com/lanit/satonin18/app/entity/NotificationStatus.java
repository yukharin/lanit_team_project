//package com.lanit.satonin18.app.entity;
//
//import lombok.*;
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "notification_statuses")
//
//@Data //get and set
////@ToString //can be loop
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//public class NotificationStatus  implements Serializable {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Basic
//    @Column(name = "name", nullable = false, length = 45)
//    private String name;
//
//    @Override
//    public String toString() {
//        return "NotificationStatus{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
