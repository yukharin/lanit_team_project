package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("actionService")
@Transactional
public class ActionServiceImp implements CrudService<Action>  {

   @Autowired
   private CrudDAO<Action> actionDAO;

   @Override
   public void saveOrUpdate(Action action) {
      actionDAO.saveOrUpdate(action);
   }

   @Override
   public void update(Action action) {
      actionDAO.update(action);
   }
   /*
       @Override
       @Transactional
       public List<Action> searchActions(String theSearchName) {
           return actionDAO.searchActions(theSearchName);
       }
   */
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

}