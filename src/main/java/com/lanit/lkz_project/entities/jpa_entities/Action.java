package com.lanit.lkz_project.entities.jpa_entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lanit.lkz_project.entities.enums.ActionType;
import com.lanit.lkz_project.entities.enums.NotificationStatus;
import com.lanit.lkz_project.entities.validation_groups.ActionValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

    @NotNull(groups = ActionValidationGroup.class)
    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

    @NotNull(groups = ActionValidationGroup.class)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "action_type")
    private ActionType actionType;

    @NotBlank(groups = ActionValidationGroup.class)
    @Size(min = 5, max = 300, groups = ActionValidationGroup.class)
    @Column(length = 300)
    private String content;


    @NotNull
    @PastOrPresent
    @Column(nullable = false, name = "date")
    private LocalDateTime date;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id")
    private User implementor;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus statusAfterAction;


}
