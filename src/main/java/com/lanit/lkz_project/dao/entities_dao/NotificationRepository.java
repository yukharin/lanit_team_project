package com.lanit.lkz_project.dao.entities_dao;

import com.lanit.lkz_project.dao.application_dao.NotificationRepositoryCustom;
import com.lanit.lkz_project.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {


}
