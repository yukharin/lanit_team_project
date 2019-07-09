package com.lanit.lkz_project.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "notifications")
@Getter
@Setter
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
    private Set<Action> actions;

}

