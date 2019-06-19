package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Organization organization;

    @Column(name = "notification_type")
    private String notificationType;

    @OneToOne
    private NotificationStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_received")
    private Date dateRecieved;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_response")
    private Date dateResponse;

    @Column(name = "letter_number")
    private String letterNumber;

    @OneToOne
    private User userCuratorGos;

    @OneToOne
    private User userImplementor;

    @OneToMany
    private List<Action> actions;


}
