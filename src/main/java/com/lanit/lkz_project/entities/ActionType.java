package com.lanit.lkz_project.entities;

import javax.persistence.*;

@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private enum Type {
        DIRECTING_RESPONSE, CONFORM_RESPONSE, REJECT_RESPONSE;
    }


}
