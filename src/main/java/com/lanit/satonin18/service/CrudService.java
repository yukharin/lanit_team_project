package com.lanit.satonin18.service;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface CrudService<T> {
    public void saveOrUpdate(T organization) ;

    public void update(T organization) ;

    //   public List<Organization> searchOrganizations(String theSearchName) ;

    public void delete(int id) ;

    public T getById(int id) ;

    public List<T> list() ;
}
