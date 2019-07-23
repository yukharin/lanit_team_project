package com.lanit.satonin18.mvc.service;

import java.util.List;

import com.lanit.satonin18.mvc.dao.UserDAO;
import com.lanit.satonin18.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImp implements UserService  {

   @Autowired
   private UserDAO userDAO;

   @Override
   public List<User> searchUserByLastName(String theSearchName) {
      return userDAO.searchUserByLastName(theSearchName);
   }

   @Override
   public void saveOrUpdate(User user) {
      userDAO.saveOrUpdate(user);
   }

   @Override
   public void update(User user) {
      userDAO.update(user);
   }

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