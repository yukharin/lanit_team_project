package com.lanit.satonin18.app.entity;

import com.lanit.satonin18.app.entity.no_db.Status;
import lombok.*;
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

    @ManyToOne
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    private Organization organization;

//    @ManyToOne
//    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_notification_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_user_curator_gos", referencedColumnName = "id", nullable = false)
    private User userByIdUserCuratorGos;

    @ManyToOne
    @JoinColumn(name = "id_user_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdUserImplementor;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "notification")//, fetch = FetchType.EAGER)
    private List<Action> actions;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationType='" + notificationType + '\'' +
                ", dateReceived=" + dateReceived +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
//                ", organization=" + organization +
                ", status=" + status +
                ", userByIdUserCuratorGos=" + userByIdUserCuratorGos +
                ", userByIdUserImplementor=" + userByIdUserImplementor +
                ", actions.size()=" + actions.size() +
                ", actions" + actions +
                '}';
    }
}
