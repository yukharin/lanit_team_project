package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.User;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface UserDAO extends CrudDAO<User> {

   public List<User> searchUserByLastName(String theSearchName) ;

   @Override
   void saveOrUpdate(User entity);

   @Override
   void update(User entity) ;


   @Override
   void delete(int id) ;

   @Override
   User getById(int id) ;

   @Override
   List<User> list() ;
}
