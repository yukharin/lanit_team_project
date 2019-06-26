package com.lanit.satonin18.dao.no_use;


import com.lanit.satonin18.dao.CrudDAO;
import com.lanit.satonin18.model.Organization;

import java.util.List;

public interface OrganizationDAO extends CrudDAO<Organization> {
    public void saveOrUpdate(Organization organization);

    public void update(Organization organization) ;

//    public List<Organization> searchOrganizations(String theSearchName);

    public void delete(int id) ;

    public Organization getById(int id) ;

    public List<Organization> list() ;
}
