package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.Organization;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("organizationDAO")
public class OrganizationDAOImp implements CrudDAO<Organization> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveOrUpdate(Organization organization) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
        sessionFactory.getCurrentSession()
                .saveOrUpdate(organization);
    }

    @Override
    @Transactional
    public void update(Organization organization) {
        sessionFactory.getCurrentSession()
                .update(organization);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Organization organization = session.load(Organization.class, id);
        if(organization != null)
            session.delete(organization);
    }

    @Override
    public Organization getById(int id) {
        try(final Session session = sessionFactory.openSession();){
            Organization organization = session.get(Organization.class, id);
            return organization;
        }
    }

    @Override
    public List<Organization> list() {
        try(final Session session = sessionFactory.openSession();){
            List<Organization> organizations = session.createQuery("from Organization", Organization.class).list();
            return organizations;
        }
    }
}