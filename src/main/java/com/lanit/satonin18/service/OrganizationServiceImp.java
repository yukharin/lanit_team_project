package com.lanit.satonin18.service;

//import org.apache.log4j.Logger;
import com.lanit.satonin18.dao.CrudDAO;
import com.lanit.satonin18.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("organizationService")
@Transactional
public class OrganizationServiceImp  implements CrudService<Organization> {

    @Autowired
    private CrudDAO<Organization> organizationDAO;

    @Override
    public void saveOrUpdate(Organization organization) {
        organizationDAO.saveOrUpdate(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationDAO.update(organization);
    }
/*
    @Override
    public List<Organization> searchOrganizations(String theSearchName) {
        return organizationDAO.searchOrganizations(theSearchName);
    }
*/
    @Override
    public void delete(int id) {
        organizationDAO.delete(id);
    }

    @Override
    public Organization getById(int id) {
        return organizationDAO.getById(id);
    }

    @Override
    public List<Organization> list() {
        return organizationDAO.list();
    }
}