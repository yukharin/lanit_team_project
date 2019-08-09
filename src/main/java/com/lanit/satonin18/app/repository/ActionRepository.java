package com.lanit.satonin18.app.repository;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository
        extends JpaRepository<Action, Integer>,
        ActionRepositoryCustomized
{
   List<Action> findByNotification(Notification notification);
}