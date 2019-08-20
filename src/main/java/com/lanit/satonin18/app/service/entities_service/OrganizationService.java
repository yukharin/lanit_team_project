package com.lanit.satonin18.app.service.entities_service;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.service.CrudService;

import java.util.List;

//CRUD - Create Read_by _id Update Delete
public interface OrganizationService extends CrudService<Organization> {

    @Override
    void save(Organization organization) ;

    @Override
    void delete(Organization organization) ;

    @Override
    Organization findById(int id) ;

    @Override
    List<Organization> findAll() ;

    void deleteById(int id) ;

    List<Organization> findByNameIgnoreCaseContaining(String theSearchName);
}
