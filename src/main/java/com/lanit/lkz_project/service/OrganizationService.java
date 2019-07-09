package com.lanit.lkz_project.service;

import com.lanit.lkz_project.dao.OrganizationDAO;
import com.lanit.lkz_project.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Transactional
    public void addOrganization(@NotNull Organization organization) {
        organizationDAO.addOrganization(organization);
    }

    @Transactional
    public void updateOrganization(@NotNull Organization organization) {
        organizationDAO.updateOrganization(organization);
    }

    @Transactional
    public void removeOrganization(@NotNull Organization organization) {
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

    @PostConstruct
    private void p() {
        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
