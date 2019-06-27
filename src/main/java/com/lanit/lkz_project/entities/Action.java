package com.lanit.lkz_project.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "actions")
public class Action {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @OneToOne
    @JoinColumn(name = "id_action_type", referencedColumnName = "id", nullable = false)
    private ActionType actionType;

    @Column(length = 300)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User implementor;

    @OneToOne
    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    private NotificationStatus status;

    public Action() {
    }

    public Action(Notification notification, ActionType actionType, String content, Date date, User implementor, NotificationStatus status) {
        this.notification = notification;
        this.actionType = actionType;
        this.content = content;
        this.date = date;
        this.implementor = implementor;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", notification=" + notification +
                ", actionType=" + actionType +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", implementor=" + implementor +
                ", status=" + status +
                '}';
    }
}
