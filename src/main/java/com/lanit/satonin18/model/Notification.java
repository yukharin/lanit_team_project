package com.lanit.satonin18.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "notifications")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    private/*protected*/ Organization notificOrg;

    @Basic
    @Column(name = "notification_type", nullable = true, length = 150)
    private String notificationType;

    @Basic
    @Column(name = "date_received", nullable = false)
    private Date dateReceived;

    @Basic
    @Column(name = "date_response", nullable = false)
    private Date dateResponse;

    @Basic
    @Column(name = "letter_number", nullable = true, length = 12)
    private String letterNumber;

//    @OneToMany(mappedBy = "notification")
//    private List<Action> actions;



//    @ManyToOne
//    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
//    private NotificationStatus notificationStatusByIdNotificationStatus;

    @ManyToOne
    @JoinColumn(name = "id_user_curator_gos", referencedColumnName = "id", nullable = false)
    private User userByIdUserCuratorGos;

    @ManyToOne
    @JoinColumn(name = "id_user_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdUserImplementor;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", dateReceived=" + dateReceived +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
//                ", actionsById=" + actions +
                ", userOrg=" + notificOrg +
//                ", notificationStatusByIdNotificationStatus=" + notificationStatusByIdNotificationStatus +
//                ", userByIdUserCuratorGos=" + userByIdUserCuratorGos +
//                ", userByIdUserImplementor=" + userByIdUserImplementor +
                '}';
    }
}
