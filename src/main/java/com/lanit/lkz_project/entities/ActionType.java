package com.lanit.lkz_project.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@ToString
@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private TypeOfAction name;

    public enum TypeOfAction {
        Обработать, Отправить_в_обработку
    }

}
