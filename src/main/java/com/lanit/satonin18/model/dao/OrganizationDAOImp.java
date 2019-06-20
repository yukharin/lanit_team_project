package com.lanit.satonin18.model.dao;

import com.lanit.satonin18.model.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrganizationDAO")
public class OrganizationDAOImp implements OrganizationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrganization(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.update(organization);
    }

    @Override
    public List<Organization> searchOrganizations(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;
        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Organization where firstName like :theName or lastName like :theName", Organization.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Organization", Organization.class);
        }
        List<Organization> organizations = theQuery.getResultList();
        return organizations;
    }

    @Override
    public void removeOrganization(int id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.load(Organization.class, id);

        if(organization != null)
            session.delete(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.get(Organization.class, id);
        return organization;
    }

    @Override
    public List<Organization> organizations() {
        Session session = sessionFactory.getCurrentSession();
        List<Organization> organizations = session.createQuery("from Organization order by lastName", Organization.class).list();
        return organizations;
    }
}