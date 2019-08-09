package com.lanit.satonin18.app.repository;

import com.lanit.satonin18.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Integer>,
        NotificationRepositoryCustomized
{
}
