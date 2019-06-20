package com.lanit.lkz_project.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Notification notification;

    @OneToOne
    private ActionType actionType;

    @Column
    private String content;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    @OneToOne
    private User implementor;

    @OneToOne
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getImplementor() {
        return implementor;
    }

    public void setImplementor(User implementor) {
        this.implementor = implementor;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
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
