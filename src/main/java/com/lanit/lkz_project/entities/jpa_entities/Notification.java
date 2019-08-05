package com.lanit.lkz_project.entities.jpa_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_org")
    private Organization organization;

    @NotBlank
    @Size(min = 7, max = 150, message = "Notification type should have " +
            "at least 7 characters and to have max length at least 150 characters.")
    @Column(name = "notification_type")
    private String notificationType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notification_status")
    private NotificationStatus status;


    @Temporal(TemporalType.DATE)
    @Column(name = "date_received")
    private Date dateReceived;


    @NotNull
    @Future
    @Temporal(TemporalType.DATE)
    @Column(name = "date_response")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateResponse;


    //    @NotBlank
//    @Size(min = 12, max = 12, message = "Length must be exactly 12 characters.")
    @Column(name = "letter_number")
    private String letterNumber;


    //    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_notification_author")
    private User userNotificationAuthor;

    @OneToMany(mappedBy = "notification", fetch = FetchType.EAGER)
    private Set<Action> actions;


}

