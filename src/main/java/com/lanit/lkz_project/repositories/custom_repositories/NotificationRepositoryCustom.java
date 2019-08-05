package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import org.springframework.data.domain.PageImpl;


public interface NotificationRepositoryCustom {
    PageImpl<Notification> getAccountPage(PersonalAccountPageDto<Notification> page, User user);

}
