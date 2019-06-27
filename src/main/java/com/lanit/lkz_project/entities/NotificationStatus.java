package com.lanit.lkz_project.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notification_statuses")
public class NotificationStatus {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        NEW, AWAITING_PROCESSING, THE_RESPONSE_IS_NOT_RECIEVED, RESPONSE_SENT, PROCESSED
    }

    public NotificationStatus() {
    }

    public NotificationStatus(Status status) {
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
