package com.lanit.lkz_project.repositories.entitity_repositories;

import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.repositories.custom_repositories.NotificationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {


}
