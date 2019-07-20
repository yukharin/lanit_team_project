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
//@NoArgsConstructor
//@AllArgsConstructor
public class Action implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "content", nullable = true, length = 300)
    private String content;

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

    public Action() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
    }

    public Action(int id, String content, Timestamp date, Notification notification, ActionType actionType, User userByIdImplementor, NotificationStatus notificationStatusAfterProcessing) {
        super();

        this.id = id;
        this.content = content;
        this.date = date;
        this.notification = notification;
        this.actionType = actionType;
        this.userByIdImplementor = userByIdImplementor;
        this.notificationStatusAfterProcessing = notificationStatusAfterProcessing;
    }

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
