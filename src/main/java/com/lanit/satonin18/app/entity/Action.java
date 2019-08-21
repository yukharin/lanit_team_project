package com.lanit.satonin18.app.entity;

import com.lanit.satonin18.app.entity.enum_type.ActionType;
import com.lanit.satonin18.app.entity.enum_type.Status;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "actions")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Action implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2, max = 300)
    @Column(name = "content", nullable = true, length = 300)
    private String content;

    @NotNull
    @PastOrPresent
    @Column(name = "date", nullable = false)
    private Timestamp date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_notification", referencedColumnName = "id", nullable = false)
    private Notification notification;

//    @ManyToOne
//    @JoinColumn(name = "id_action_type", referencedColumnName = "id", nullable = false)
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_action_type")
    private ActionType actionType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdImplementor;

//    @ManyToOne
//    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_notification_status")
    private Status statusAfterProcessing;

    public Action() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
    }

    public Action(int id, String content, Timestamp date, Notification notification, ActionType actionType, User userByIdImplementor, Status statusAfterProcessing) {
        super();

        this.id = id;
        this.content = content;
        this.date = date;
        this.notification = notification;
        this.actionType = actionType;
        this.userByIdImplementor = userByIdImplementor;
        this.statusAfterProcessing = statusAfterProcessing;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
//                ", notification=" + notification +
                ", actionType=" + actionType +
                ", userByIdImplementor=" + userByIdImplementor +
                ", statusAfterProcessing=" + statusAfterProcessing +
                '}';
    }
}
