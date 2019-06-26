package com.lanit.satonin18.service;

import java.util.List;

//CrUD - CREATE UPDATE DELETE
public interface CrudService<T> {
    public void saveOrUpdate(T organization) ;

    public void update(T organization) ;

    //   public List<Organization> searchOrganizations(String theSearchName) ;

    public void delete(int id) ;

    public T getById(int id) ;

    public List<T> list() ;
}
