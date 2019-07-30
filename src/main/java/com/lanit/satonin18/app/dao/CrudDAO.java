package com.lanit.satonin18.app.dao;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface CrudDAO<T> {
   void saveOrUpdate(T entity);

   void update(T entity) ;

   void delete(int id) ;

   T getById(int id) ;

   List<T> list() ;
}
