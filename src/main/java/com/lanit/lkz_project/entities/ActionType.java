package com.lanit.lkz_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 45, nullable = false)
    private Type type;

    public enum Type {
        DIRECTING_RESPONSE, CONFORM_RESPONSE, REJECT_RESPONSE
    }


    @Override
    public String toString() {
        return "ActionType{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
