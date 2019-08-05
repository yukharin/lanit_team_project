package com.lanit.lkz_project.entities.jpa_entities;

import com.lanit.lkz_project.entities.enums.NotificationStatus;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;


@StaticMetamodel(Notification.class)
public abstract class Notification_ {

    public static volatile SingularAttribute<Notification, Organization> organization;
    public static volatile SingularAttribute<Notification, LocalDate> dateResponse;
    public static volatile SingularAttribute<Notification, NotificationStatus> status;

}
