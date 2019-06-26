package com.lanit.satonin18.service;

import java.util.List;

import com.lanit.satonin18.dao.CrudDAO;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.dao.no_use.UserDAO;
import com.lanit.satonin18.service.no_use.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImp implements CrudService<User>  {

   @Autowired
   private CrudDAO<User> userDAO;

   @Override
   public void saveOrUpdate(User user) {
      userDAO.saveOrUpdate(user);
   }

   @Override
   public void update(User user) {
      userDAO.update(user);
   }
   /*
       @Override
       @Transactional
       public List<User> searchUsers(String theSearchName) {
           return userDAO.searchUsers(theSearchName);
       }
   */
   @Override
   public void delete(int id) {
      userDAO.delete(id);
   }

   @Override
   public User getById(int id) {
      return userDAO.getById(id);
   }

   @Override
   public List<User> list() {
      return userDAO.list();
   }
}