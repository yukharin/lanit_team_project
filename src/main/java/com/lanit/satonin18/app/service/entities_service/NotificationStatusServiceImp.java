//package com.lanit.satonin18.app.service.entities_service;
//
//import com.lanit.satonin18.app.dao.NotificationStatusDAO;
//import com.lanit.satonin18.app.entity.Status;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service("notificationStatusService")
//public class NotificationStatusServiceImp implements NotificationStatusService {
//
//   @Autowired
//   private NotificationStatusDAO notificationStatusDAO;
//
//   @Override
//   public List<Status> filterIds(String[] ids){
//      return notificationStatusDAO.filterIds(ids);
//   }
//
//   @Override
//   public void saveOrUpdate(Status statusAfterProcessing) {
//      notificationStatusDAO.saveOrUpdate(statusAfterProcessing);
//   }
//
//   @Override
//   public void update(Status statusAfterProcessing) {
//      notificationStatusDAO.update(statusAfterProcessing);
//   }
//
//   @Override
//   public void delete(int id) {
//      notificationStatusDAO.delete(id);
//   }
//
//   @Override
//   public Status getById(int id) {
//      return notificationStatusDAO.getById(id);
//   }
//
//   @Override
//   public List<Status> list() {
//      return notificationStatusDAO.list();
//   }
//
//   public List<Status> listByIds(List<Integer> ids){
//      return notificationStatusDAO.listByIds(ids);
//   }
//}