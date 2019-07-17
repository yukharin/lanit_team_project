package com.lanit.lkz_project.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "action_type")
    private ActionType actionType;

    @NotBlank
    @Size(min = 5, max = 300, message = "Length of comment should have at least 5 characters and should have maximum 300 characters")
    @Column(length = 300)
    private String content;


    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User implementor;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus statusAfterAction;


}
