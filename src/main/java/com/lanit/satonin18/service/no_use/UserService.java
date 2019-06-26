package com.lanit.satonin18.service.no_use;

import com.lanit.satonin18.model.User;

import java.util.List;

public interface UserService {
   public void saveOrUpdate(User user) ;

   public void update(User user) ;

   //   public List<User> searchUsers(String theSearchName) ;

   public void delete(int id) ;

   public User getById(int id) ;

   public List<User> list() ;
}
