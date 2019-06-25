package com.lanit.satonin18.service;


//import org.apache.log4j.Logger;

import com.lanit.satonin18.dao.OrganizationDAO;
import com.lanit.satonin18.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("organizationService")
@Transactional
public class OrganizationServiceImp  implements OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;
    //private OrganizationDAO organizationDAO = new OrganizationDAOImp();

    @Override
    @Transactional
    public void saveOrUpdate(Organization organization) {
        organizationDAO.saveOrUpdate(organization);
    }

    @Override
    @Transactional
    public void update(Organization organization) {
        organizationDAO.update(organization);
    }
/*
    @Override
    @Transactional
    public List<Organization> searchOrganizations(String theSearchName) {
        return organizationDAO.searchOrganizations(theSearchName);
    }
*/
    @Override
    @Transactional
    public void delete(int id) {
        organizationDAO.delete(id);
    }

    @Override
    @Transactional
    public Organization getById(int id) {
        return organizationDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Organization> list() {
        return organizationDAO.list();
    }
}