package com.lanit.satonin18.dao;

import com.lanit.satonin18.model.User;

import java.util.List;

//TODO can be ADD ABSTRACT CrudDAO<T> for all

public interface CrudDAO<T> {
   void saveOrUpdate(T entity);

   void update(T entity) ;

//    List<T> searchEntities(String theSearchName);

   void delete(int id) ;

   T getById(int id) ;

   List<T> list() ;
}
