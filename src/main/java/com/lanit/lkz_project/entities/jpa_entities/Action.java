package com.lanit.lkz_project.entities.jpa_entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lanit.lkz_project.entities.enums.ActionType;
import com.lanit.lkz_project.entities.enums.NotificationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "notification", "implementor"})
@ToString(exclude = {"notification", "implementor"})
@Entity
@Table(name = "actions")
@JsonIgnoreProperties({"notification", "implementor"})
public class Action implements Serializable {


    private static int counter = 0;

    {
        counter++;
        System.err.println("Actions: " + counter);
    }

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

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User implementor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus statusAfterAction;


}
