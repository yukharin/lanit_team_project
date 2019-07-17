package com.lanit.satonin18.mvc.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "actions")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Action implements Serializable {
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
    @Column(name = "content", nullable = true, length = 300)
    private String content;

    @Id
    @Column(name = "date", nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "id_action_type", referencedColumnName = "id", nullable = false)
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdImplementor;

    @ManyToOne
    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    private NotificationStatus notificationStatusAfterProcessing;

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
//                ", notification=" + notification +
                ", actionType=" + actionType +
                ", userByIdImplementor=" + userByIdImplementor +
                ", notificationStatusAfterProcessing=" + notificationStatusAfterProcessing +
                '}';
    }
}
