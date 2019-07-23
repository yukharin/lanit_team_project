package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.entities.PersonalAccountPage;
import com.lanit.lkz_project.entities.User;
import org.springframework.data.domain.Pageable;


public interface NotificationRepositoryCustom {
    void setStateOfPage(PersonalAccountPage<Notification> page,
                        Pageable pageable, User user);

}
