package com.lanit.satonin18.service;


import com.lanit.satonin18.model.Organization;

import java.util.List;

public interface OrganizationService {
    public void saveOrUpdate(Organization organization) ;

    public void update(Organization organization) ;

 //   public List<Organization> searchOrganizations(String theSearchName) ;

    public void delete(int id) ;

    public Organization getById(int id) ;

    public List<Organization> list() ;
}
