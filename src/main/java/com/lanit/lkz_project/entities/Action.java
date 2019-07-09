package com.lanit.lkz_project.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actions")
public class Action {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @ManyToOne
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return id == action.id &&
                Objects.equals(notification, action.notification) &&
                Objects.equals(actionType, action.actionType) &&
                Objects.equals(content, action.content) &&
                Objects.equals(date, action.date) &&
                Objects.equals(implementor, action.implementor) &&
                Objects.equals(status, action.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notification, actionType, content, date, implementor, status);
    }
}
