package com.lanit.satonin18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "notifications")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "notification_type", nullable = true, length = 150)
    private String notificationType;

    @Basic
    @Column(name = "date_received", nullable = false)
    private Date dateReceived;

    @Basic
    @Column(name = "date_response", nullable = false)
    private Date dateResponse;

    @Basic
    @Column(name = "letter_number", nullable = true, length = 12)
    private String letterNumber;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", dateReceived=" + dateReceived +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
                '}';
    }
}
