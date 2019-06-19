package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "notifications", schema = "lanit", catalog = "")
public class NotificationsEntity {
    private int id;
    private Date dateNotificationReceived;
    private Date dateResponse;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_notification_received")
    public Date getDateNotificationReceived() {
        return dateNotificationReceived;
    }

    public void setDateNotificationReceived(Date dateNotificationReceived) {
        this.dateNotificationReceived = dateNotificationReceived;
    }

    @Basic
    @Column(name = "date_response")
    public Date getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsEntity that = (NotificationsEntity) o;
        return id == that.id &&
                Objects.equals(dateNotificationReceived, that.dateNotificationReceived) &&
                Objects.equals(dateResponse, that.dateResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateNotificationReceived, dateResponse);
    }
}
