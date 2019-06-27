package com.lanit.lkz_project.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_org", nullable = false)
    private Organization organization;

    @Column(name = "notification_type", length = 150)
    private String notificationType;

    @OneToOne
    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    private NotificationStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_received", nullable = false)
    private Date dateRecieved;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_response", nullable = false)
    private Date dateResponse;

    @Column(name = "letter_number", length = 12)
    private String letterNumber;

    @OneToOne
    @JoinColumn(name = "id_user_curator_gos", referencedColumnName = "id", nullable = false)
    private User userCuratorGos;

    @OneToOne
    @JoinColumn(name = "id_user_implementor", referencedColumnName = "id", nullable = false)
    private User userImplementor;

    @OneToMany(mappedBy = "notification")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Action> actions;

    public Notification() {
    }

    public Notification(Organization organization, String notificationType, NotificationStatus status, Date dateRecieved, Date dateResponse,
                        String letterNumber, User userCuratorGos, User userImplementor, List<Action> actions) {
        this.organization = organization;
        this.notificationType = notificationType;
        this.status = status;
        this.dateRecieved = dateRecieved;
        this.dateResponse = dateResponse;
        this.letterNumber = letterNumber;
        this.userCuratorGos = userCuratorGos;
        this.userImplementor = userImplementor;
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", organization=" + organization +
                ", notificationType='" + notificationType + '\'' +
                ", status=" + status +
                ", dateRecieved=" + dateRecieved +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
                ", userCuratorGos=" + userCuratorGos +
                ", userImplementor=" + userImplementor +
                ", actions=" + actions +
                '}';
    }
}
