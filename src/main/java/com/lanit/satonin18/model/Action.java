package com.lanit.satonin18.model;

import com.lanit.satonin18.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "actions")
@IdClass(ActionPK.class)

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "content", nullable = true, length = 300)
    private String content;

    @Id
    @Column(name = "date", nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notificationByIdNotification;

    @ManyToOne
    @JoinColumn(name = "id_action_type", referencedColumnName = "id", nullable = false)
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdImplementor;

    @ManyToOne
    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    private NotificationStatus notificationStatusAfterProcessing;

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
//                ", notificationByIdNotification=" + notificationByIdNotification +
                ", actionTypByIdActionType=" + actionType +
                ", userByIdImplementor=" + userByIdImplementor +
                ", notificationStatusAfterProcessing=" + notificationStatusAfterProcessing +
                '}';
    }
}
