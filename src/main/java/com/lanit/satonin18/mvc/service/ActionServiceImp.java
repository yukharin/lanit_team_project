package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.dao.ActionDAO;
import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("actionService")
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

    @Override
    public void save(Action action) {  actionDAO.save(action); }

   @PostConstruct
   private void p() {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   }

   public List<Action> listByIdNotification(Notification notification) {
      return actionDAO.listByIdNotification(notification);
   }

   public Pagination<Action> filter_Notific_Order_Pagination(Notification notification, String orderFieldName, boolean desc, Pagination<Action> actionPagination){
      return actionDAO.filter_Notific_Order_Pagination(notification, orderFieldName, desc, actionPagination);
   }

}