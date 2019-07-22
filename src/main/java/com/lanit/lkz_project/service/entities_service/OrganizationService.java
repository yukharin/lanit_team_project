package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.entities_dao.OrganizationDAO;
import com.lanit.lkz_project.entities.Organization;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Transactional
    public void addOrganization(@NonNull Organization organization) {
        organizationDAO.addOrganization(organization);
    }

    @Transactional
    public void updateOrganization(@NonNull Organization organization) {
        organizationDAO.updateOrganization(organization);
    }

    @Transactional
    public void removeOrganization(@NonNull Organization organization) {
        organizationDAO.removeOrganization(organization);
    }

    @Transactional
    public void removeOrganization(long id) {
        organizationDAO.removeOrganization(id);
    }

    @Transactional
    public Organization getOrganization(long id) {
        return organizationDAO.getOrganization(id);
    }

    @Transactional
    public List<Organization> organizations() {
        return organizationDAO.organizations();
    }

    @Transactional
    public List<Organization> nonGovernmentOrganizations() {
        return organizationDAO.nonGovernmentOrganizations();
    }

    @PostConstruct
    private void p() {
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
