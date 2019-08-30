package com.lanit.satonin18.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lanit.satonin18.app.entity.enum_type.Status;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "notifications")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties({"actions"})
public class Notification
        implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "notification_type", nullable = true, length = 150)
    private String notificationType;

    @NotNull
//    @PastOrPresent
    @Column(name = "date_received", nullable = false)
    private java.sql.Date dateReceived;

    @NotNull
//    @PastOrPresent
//    @Future
    @Column(name = "date_response", nullable = false)
    private java.sql.Date dateResponse;

//    @NotBlank
    @Size(min = 12, max = 12)
    @Column(name = "letter_number", nullable = true, length = 12)
    private String letterNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_org")//, referencedColumnName = "id", nullable = false)
    private Organization organization;

//    @ManyToOne
//    @JoinColumn(name = "id_notification_status", referencedColumnName = "id", nullable = false)
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_notification_status")
    private Status status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user_curator_gos", referencedColumnName = "id", nullable = false)
    private User userByIdUserCuratorGos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user_implementor", referencedColumnName = "id", nullable = false)
    private User userByIdUserImplementor;

//    @NotEmpty
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
