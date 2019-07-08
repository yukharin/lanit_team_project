package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_org", nullable = false)
    private Organization organization;

    @Column(name = "notification_type", length = 150)
    private String notificationType;

    @ManyToOne
    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    private NotificationStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_received", nullable = false)
    private Date dateRecieved;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_response", nullable = false)
    private Date dateResponse;

    @Column(name = "letter_number", length = 12)
    private String letterNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_notification_author", referencedColumnName = "id", nullable = false)
    private User userImplementor;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Action> actions;


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", organization=" + organization +
                ", notificationType='" + notificationType;
    }
}
