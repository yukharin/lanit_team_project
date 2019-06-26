package com.lanit.satonin18.dao.no_use;

import com.lanit.satonin18.dao.CrudDAO;
import com.lanit.satonin18.model.User;

import java.util.List;

//TODO can be ADD ABSTRACT CrudDAO<T> for all

public interface UserDAO  extends CrudDAO<User> {
   void saveOrUpdate(User user);

   void update(User user) ;

//    List<User> searchUsers(String theSearchName);

   void delete(int id) ;

   User getById(int id) ;

   List<User> list() ;
}
