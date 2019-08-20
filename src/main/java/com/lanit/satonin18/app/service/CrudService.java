package com.lanit.satonin18.app.service;

import java.util.List;

public interface CrudService<T>{
    void save(T entity);

    void delete(T entity) ;

    T findById(int id) ;

    List<T> findAll() ;
}