package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.Organization;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void addOrganization(@NonNull Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(organization);
    }


    public void updateOrganization(@NonNull Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(organization);
    }

    public void removeOrganization(@NonNull Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(organization);
    }

    public void removeOrganization(long id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.load(Organization.class, id);
        if (organization != null) {
            session.delete(organization);
        }
    }

    public Organization getOrganization(long id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.get(Organization.class, id);
        return organization;
    }

    public List<Organization> organizations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Organization ORDER BY name").list();
    }

    public List<Organization> nonGovernmentOrganizations() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Organization WHERE government = false ORDER BY name ").list();
    }

}
