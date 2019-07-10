package com.lanit.satonin18.mvc.service;

import com.lanit.satonin18.mvc.entity.Organization;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface OrganizationService extends CrudService<Organization> {

    @Override
    void saveOrUpdate(Organization organization) ;

    @Override
    void update(Organization organization) ;

    @Override
    void delete(int id) ;

    @Override
    Organization getById(int id) ;

    @Override
    List<Organization> list() ;
}
