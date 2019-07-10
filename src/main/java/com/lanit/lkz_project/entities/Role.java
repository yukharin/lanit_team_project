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
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public enum UserRole {
        Чиновник, Работник_организации
    }

}

