package com.lanit.satonin18.app;

import java.util.List;

public interface Crud<T>{
    void save(T entity);

    void update(T entity) ;

    void delete(T entity) ;

    T getById(int id) ;

    List<T> list() ;
}
