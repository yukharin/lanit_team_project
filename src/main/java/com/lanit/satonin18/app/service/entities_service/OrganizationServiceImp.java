package com.lanit.satonin18.app.service.entities_service;

//import org.apache.log4j.Logger;
import com.lanit.satonin18.app.dao.CrudDAO;
import com.lanit.satonin18.app.dao.OrganizationDAO;
import com.lanit.satonin18.app.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("organizationService")
public class OrganizationServiceImp  implements OrganizationService {
    @Autowired
    private OrganizationDAO organizationDAO;

    @Override
    public void save(Organization organization) {
        organizationDAO.save(organization);
    }

    @Override
    public void update(Organization organization) {
        organizationDAO.update(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationDAO.delete(organization);
    }

    @Override
    public Organization getById(int id) {
        return organizationDAO.getById(id);
    }

    @Override
    public List<Organization> list() {
        return organizationDAO.list();
    }

    public void deleteById(int id) {
        organizationDAO.deleteById(id);
    }
}