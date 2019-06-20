package com.lanit.lkz_project.entities;

import javax.persistence.*;

@Entity
@Table(name="notification_statuses")
public class NotificationStatus {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Status status;

    private enum Status {
        AWAITING_PROCESSING, THE_RESPONSE_IS_NOT_RECIEVED, RESPONSE_SENT, PROCESSED;
    }

    public NotificationStatus() {
    }

    public NotificationStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NotificationStatus{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
