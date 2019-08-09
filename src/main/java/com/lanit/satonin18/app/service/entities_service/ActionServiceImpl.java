package com.lanit.satonin18.app.service.entities_service;

//import com.lanit.satonin18.app.Pagination;
import com.lanit.satonin18.app.repository.ActionRepository;
import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("actionService")
public class ActionServiceImpl implements ActionService {
   @Autowired
   private ActionRepository actionRepository;

   @Override
   public void save(Action action) {
      actionRepository.save(action);
   }

   @Override
   public void delete(Action action) {
      actionRepository.delete(action);
   }

   @Override
   public Action findById(int id) {
      return actionRepository.findById(id).get();
   }

   @Override
   public List<Action> findAll() {
      return actionRepository.findAll();
   }

   @Override
   public void deleteById(int id) {
      actionRepository.deleteById(id);
   }

   @PostConstruct
   private void p() {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   }

   public List<Action> listByIdNotification(Notification notification) {
      return actionRepository.findByNotification(notification);
   }

   public PageImpl<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pageable actionPagination){
      return actionRepository.filter_Notific_Order_Pagination(notification, orderFieldName, desc, actionPagination);
   }

}