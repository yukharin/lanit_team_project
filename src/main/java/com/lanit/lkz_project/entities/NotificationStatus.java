package com.lanit.lkz_project.entities;

import javax.persistence.*;

@Entity
public class NotificationStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Status status;

    private enum Status {
        AWAITING_PROCESSING, THE_RESPONSE_IS_NOT_RECIEVED, RESPONSE_SENT, PROCESSED;
    }


}
