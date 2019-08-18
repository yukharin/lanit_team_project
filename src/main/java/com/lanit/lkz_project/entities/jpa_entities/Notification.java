package com.lanit.lkz_project.entities.jpa_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lanit.lkz_project.entities.enums.NotificationStatus;
import com.lanit.lkz_project.entities.validation_groups.NotificationValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "organization", "actions"})
@ToString(exclude = {"organization", "actions"})
@Entity
@Table(name = "notifications")
@JsonIgnoreProperties({"actions"})
public class Notification implements Serializable {

    private static int counter = 0;

    {
        counter++;
        System.err.println("Notifications: " + counter);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(groups = NotificationValidationGroup.class)
    @ManyToOne
    @JoinColumn(name = "id_org")
    private Organization organization;

    @NotBlank(groups = NotificationValidationGroup.class)
    @Size(min = 7, max = 150, groups = NotificationValidationGroup.class)
    @Column(name = "notification_type")
    private String notificationType;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus status;


    @NotNull
    @PastOrPresent
    @Column(name = "date_received")
    private LocalDate dateReceived;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(groups = NotificationValidationGroup.class)
    @Future(groups = NotificationValidationGroup.class)
    @Column(name = "date_response")
    private LocalDate dateResponse;

    @NotBlank
    @Size(min = 12, max = 12, message = "Length must be exactly 12 characters.")
    @Column(name = "letter_number")
    private String letterNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_notification_author")
    private User userNotificationAuthor;

    @OneToMany(mappedBy = "notification", fetch = FetchType.EAGER)
    private Set<Action> actions;


}

