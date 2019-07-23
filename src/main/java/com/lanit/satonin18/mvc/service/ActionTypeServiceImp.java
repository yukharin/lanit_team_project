package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("actionTypeService")
public class ActionTypeServiceImp implements CrudService<ActionType>  {

   @Autowired
   private CrudDAO<ActionType> actionTypeDAO;

   @Override
   public void saveOrUpdate(ActionType actionType) {
      actionTypeDAO.saveOrUpdate(actionType);
   }

   @Override
   public void update(ActionType actionType) {
      actionTypeDAO.update(actionType);
   }
   /*
       @Override
       @Transactional
       public List<ActionType> searchActionTypes(String theSearchName) {
           return actionTypeDAO.searchActionTypes(theSearchName);
       }
   */
   @Override
   public void delete(int id) {
      actionTypeDAO.delete(id);
   }

   @Override
   public ActionType getById(int id) {
      return actionTypeDAO.getById(id);
   }

   @Override
   public List<ActionType> list() {
      return actionTypeDAO.list();
   }
}