package com.lanit.satonin18.model.service;

import com.lanit.satonin18.model.dao.OrganizationDAO;
import com.lanit.satonin18.model.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationService {
    public void addOrganization(Organization organization) ;

    public void updateOrganization(Organization organization) ;

    public List<Organization> searchOrganizations(String theSearchName) ;

    public void removeOrganization(int id) ;

    public Organization getOrganizationById(int id) ;

    public List<Organization> organizations() ;
}
