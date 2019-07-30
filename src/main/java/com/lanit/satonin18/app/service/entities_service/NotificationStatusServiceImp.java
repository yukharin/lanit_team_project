package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.dao.NotificationStatusDAO;
import com.lanit.satonin18.app.entity.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("notificationStatusService")
public class NotificationStatusServiceImp implements NotificationStatusService {

   @Autowired
   private NotificationStatusDAO notificationStatusDAO;

   @Override
   public List<NotificationStatus> filterIds(String[] ids){
      return notificationStatusDAO.filterIds(ids);
   }

   @Override
   public void saveOrUpdate(NotificationStatus notificationStatus) {
      notificationStatusDAO.saveOrUpdate(notificationStatus);
   }

   @Override
   public void update(NotificationStatus notificationStatus) {
      notificationStatusDAO.update(notificationStatus);
   }

   @Override
   public void delete(int id) {
      notificationStatusDAO.delete(id);
   }

   @Override
   public NotificationStatus getById(int id) {
      return notificationStatusDAO.getById(id);
   }

   @Override
   public List<NotificationStatus> list() {
      return notificationStatusDAO.list();
   }

   public List<NotificationStatus> listByIds(List<Integer> ids){
      return notificationStatusDAO.listByIds(ids);
   }
}