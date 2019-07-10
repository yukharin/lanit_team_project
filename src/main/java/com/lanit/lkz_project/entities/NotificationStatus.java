package com.lanit.lkz_project.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
@Entity
@Table(name = "notification_statuses")
public class NotificationStatus {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private StatusOfNotification name;

    public enum StatusOfNotification {
        Новое, Обработано, Отправлено_в_обработку
    }

}
