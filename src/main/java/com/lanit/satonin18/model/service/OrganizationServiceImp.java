package com.lanit.satonin18.model.service;

import com.lanit.satonin18.model.dao.OrganizationDAO;
import com.lanit.satonin18.model.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("organizationService")
@Transactional
public class OrganizationServiceImp implements OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Override
    @Transactional
    public void addOrganization(Organization organization) {
        organizationDAO.addOrganization(organization);
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        organizationDAO.updateOrganization(organization);
    }

    @Override
    @Transactional
    public List<Organization> searchOrganizations(String theSearchName) {
        return organizationDAO.searchOrganizations(theSearchName);
    }

    @Override
    @Transactional
    public void removeOrganization(int id) {
        organizationDAO.removeOrganization(id);
    }

    @Override
    @Transactional
    public Organization getOrganizationById(int id) {
        return organizationDAO.getOrganizationById(id);
    }

    @Override
    @Transactional
    public List<Organization> organizations() {
        return organizationDAO.organizations();
    }
}