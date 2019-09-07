package com.lanit.lkz_project.repositories.entitity_repositories;

import com.lanit.lkz_project.entities.data_transfer_objects.JsonPageImpl;
import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;


public interface NotificationRepositoryCustom {
    JsonPageImpl<Notification> getAccountPage(PersonalAccountPageDto<Notification> page, User user);

}
