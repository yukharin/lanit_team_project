package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.User;

import java.util.List;

//TODO can be ADD ABSTRACT DAO<T> for all

public interface UserDAO {
   void saveOrUpdate(User user);

   void update(User user) ;

//    List<User> searchUsers(String theSearchName);

   void delete(int id) ;

   User getById(int id) ;

   List<User> list() ;
}
