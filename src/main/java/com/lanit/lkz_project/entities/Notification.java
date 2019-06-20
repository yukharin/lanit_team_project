package com.lanit.lkz_project.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Organization organization;

    @Column(name = "notification_type")
    @JoinColumn(name = "id_notification_status")
    private String notificationType;

    @OneToOne
    private NotificationStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_received")
    private Date dateRecieved;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_response")
    private Date dateResponse;

    @Column(name = "letter_number")
    private String letterNumber;

    @OneToOne
    private User userCuratorGos;

    @OneToOne
    private User userImplementor;

    @OneToMany
    private List<Action> actions;

    public Notification() {
    }

    public Notification(Organization organization, String notificationType, NotificationStatus status, Date dateRecieved, Date dateResponse,
                        String letterNumber, User userCuratorGos, User userImplementor, List<Action> actions) {
        this.organization = organization;
        this.notificationType = notificationType;
        this.status = status;
        this.dateRecieved = dateRecieved;
        this.dateResponse = dateResponse;
        this.letterNumber = letterNumber;
        this.userCuratorGos = userCuratorGos;
        this.userImplementor = userImplementor;
        this.actions = actions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public Date getDateRecieved() {
        return dateRecieved;
    }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    public Date getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }

    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    public User getUserCuratorGos() {
        return userCuratorGos;
    }

    public void setUserCuratorGos(User userCuratorGos) {
        this.userCuratorGos = userCuratorGos;
    }

    public User getUserImplementor() {
        return userImplementor;
    }

    public void setUserImplementor(User userImplementor) {
        this.userImplementor = userImplementor;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", organization=" + organization +
                ", notificationType='" + notificationType + '\'' +
                ", status=" + status +
                ", dateRecieved=" + dateRecieved +
                ", dateResponse=" + dateResponse +
                ", letterNumber='" + letterNumber + '\'' +
                ", userCuratorGos=" + userCuratorGos +
                ", userImplementor=" + userImplementor +
                ", actions=" + actions +
                '}';
    }
}
