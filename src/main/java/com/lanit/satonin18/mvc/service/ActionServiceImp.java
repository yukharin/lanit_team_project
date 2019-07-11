package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.dao.ActionDAO;
import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("actionService")
@Transactional
public class ActionServiceImp implements ActionService {

   @Autowired
   private ActionDAO actionDAO;

   @Override
   public void saveOrUpdate(Action action) {
      actionDAO.saveOrUpdate(action);
   }

   @Override
   public void update(Action action) {
      actionDAO.update(action);
   }

   @Override
   public void delete(int id) {
      actionDAO.delete(id);
   }

   @Override
   public Action getById(int id) {
      return actionDAO.getById(id);
   }

   @Override
   public List<Action> list() {
      return actionDAO.list();
   }

   @PostConstruct
   private void p() {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   }

   public List<Action> listByIdNotification(Notification notification) {
      return actionDAO.listByIdNotification(notification);
   }

}