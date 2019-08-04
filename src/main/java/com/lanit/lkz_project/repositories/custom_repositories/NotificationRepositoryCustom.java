package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.dto.PersonalAccountPage;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import org.springframework.data.domain.PageImpl;


public interface NotificationRepositoryCustom {
    PageImpl<Notification> getAccountPage(PersonalAccountPage<Notification> page, User user);

}
