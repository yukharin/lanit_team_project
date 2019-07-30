package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.JsonPageImpl;
import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.PersonalAccountPage;
import com.lanit.lkz_project.entities.User;


public interface NotificationRepositoryCustom {
    JsonPageImpl<Notification> getAccountPage(PersonalAccountPage<Notification> page, User user);

}
