package com.lanit.lkz_project.entities;

import javax.persistence.*;

@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private enum Type {
        DIRECTING_RESPONSE, CONFORM_RESPONSE, REJECT_RESPONSE;
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
