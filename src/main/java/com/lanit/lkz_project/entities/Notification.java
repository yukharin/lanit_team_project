package com.lanit.lkz_project.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_org")
    private Organization organization;

    @Column(name = "notification_type")
    private String notificationType;

    @ManyToOne
    @JoinColumn(name = "id_notification_status")
    private NotificationStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_received")
    private Date dateReceived;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_response")
    private Date dateResponse;

    @Column(name = "letter_number")
    private String letterNumber;

    @ManyToOne
    @JoinColumn(name = "id_user_notification_author")
    private User user;

    @OneToMany(mappedBy = "notification", fetch = FetchType.EAGER)
    private List<Action> actions;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", status=" + status +
                ", dateReceived=" + dateReceived +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id &&
                Objects.equals(organization, that.organization) &&
                Objects.equals(notificationType, that.notificationType) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dateReceived, that.dateReceived) &&
                Objects.equals(dateResponse, that.dateResponse) &&
                Objects.equals(letterNumber, that.letterNumber) &&
                Objects.equals(user, that.user) &&
                Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organization, notificationType, status, dateReceived, dateResponse, letterNumber, user, actions);
    }
}

