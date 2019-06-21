package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizationDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void addOrganization(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(organization);
    }

    public void deleteOrganization(int id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.load(Organization.class, id);
        if (organization != null) {
            session.delete(organization);
        }
    }

    public void updateOrganization(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(organization);
    }

    public Organization getOrganization(int id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.get(Organization.class, id);
        return organization;
    }

    public List<Organization> organizations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Organization ORDER BY name").list();

    }

}
