package com.lanit.lkz_project.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Notification notification;

    @OneToOne
    private ActionType actionType;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    @OneToOne
    private User implementor;

    @OneToOne
    private NotificationStatus status;


}
