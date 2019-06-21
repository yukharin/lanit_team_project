package com.lanit.satonin18.model.dao;

import com.lanit.satonin18.model.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrganizationDAO {
    public void addOrganization(Organization organization);

    public void updateOrganization(Organization organization) ;

//    public List<Organization> searchOrganizations(String theSearchName);

    public void removeOrganization(int id) ;

    public Organization getOrganizationById(int id) ;

    public List<Organization> organizations() ;
}
