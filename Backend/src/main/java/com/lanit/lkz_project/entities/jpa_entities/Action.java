package com.lanit.lkz_project.entities.jpa_entities;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonIgnoreProperties(value = {"notification"}, allowSetters = true)
public class Action implements Serializable {


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
    @Size(min = 5, max = 300)
    @Column(length = 300)
    private String content;


    @NotNull
    @PastOrPresent
    @Column(nullable = false, name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd , hh:mm")
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
