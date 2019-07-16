package com.lanit.lkz_project.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "notification", "implementor"})
@ToString(exclude = {"notification", "implementor"})
@Entity
@Table(name = "actions")
public class Action {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(length = 300)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User implementor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus statusAfterAction;


}
