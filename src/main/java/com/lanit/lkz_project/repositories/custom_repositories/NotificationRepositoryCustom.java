package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.PersonalAccountStateOfPage;
import com.lanit.lkz_project.entities.User;
import org.springframework.data.domain.Pageable;


public interface NotificationRepositoryCustom {
    void setStateOfPage(PersonalAccountStateOfPage<Notification> page,
                        Pageable pageable, User user);

}
