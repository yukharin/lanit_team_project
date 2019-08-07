package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.Organization;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface OrganizationService extends CrudService<Organization> {

    @Override
    void save(Organization organization) ;

    @Override
    void update(Organization organization) ;

    @Override
    void delete(Organization organization) ;

    @Override
    Organization getById(int id) ;

    @Override
    List<Organization> list() ;

    void deleteById(int id) ;
}
