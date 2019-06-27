package com.lanit.lkz_project.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 45, nullable = false)
    private Type type;

    public enum Type {
        DIRECTING_RESPONSE, CONFORM_RESPONSE, REJECT_RESPONSE
    }

    public ActionType() {
    }

    public ActionType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ActionType{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
