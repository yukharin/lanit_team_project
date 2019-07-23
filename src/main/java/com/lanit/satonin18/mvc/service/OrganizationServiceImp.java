package com.lanit.satonin18.mvc.service;

//import org.apache.log4j.Logger;
import com.lanit.satonin18.mvc.dao.CrudDAO;
import com.lanit.satonin18.mvc.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("organizationService")
public class OrganizationServiceImp  implements OrganizationService {

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