package com.lanit.satonin18.app.service.entities_service;

import java.util.Collections;
import java.util.List;

import com.lanit.satonin18.app.dao.UserDAO;
import com.lanit.satonin18.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImp implements UserService  {

   @Autowired
   private UserDAO userDAO;

   @Override
   public void save(User user) {
      userDAO.save(user);
   }

   @Override
   public void update(User user) {
      userDAO.update(user);
   }

   @Override
   public void delete(User user) {
      userDAO.delete(user);
   }

   @Override
   public User getById(int id) {
      return userDAO.getById(id);
   }

   @Override
   public List<User> list() {
      return userDAO.list();
   }

   @Override
   public void deleteById(int id) {
      userDAO.deleteById(id);
   }

   @Override
   public List<User> searchUserByLastName(String theSearchName) {
      return userDAO.searchUserByLastName(theSearchName);
   }
}