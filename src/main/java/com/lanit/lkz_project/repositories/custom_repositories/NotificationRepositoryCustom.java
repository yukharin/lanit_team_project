package com.lanit.lkz_project.repositories.custom_repositories;

import com.lanit.lkz_project.entities.PersonalAccountStateOfPage;
import org.springframework.data.domain.Pageable;


public interface NotificationRepositoryCustom {


    void getAccountNotifications(PersonalAccountStateOfPage page, Pageable pageable);


}
