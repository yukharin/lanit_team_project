package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.Organization;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("organizationDAO") //TODO need @NotNull final IN ARG //throws Exc
public class OrganizationDAOImp implements OrganizationDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//    protected Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    @Transactional
    public void update(Organization organization) {
        em.merge(organization);
    }

    @Override
    @Transactional
    public void delete(Organization organization) {
        em.remove(organization);
    }

    @Override
    public Organization getById(int id) {
        return em.find(Organization.class, id);
    }

    @Override
    public List<Organization> list() {
        TypedQuery<Organization> typedQuery = em.createQuery("from Organization", Organization.class);
        return typedQuery.getResultList();
//        }
    }

    @Transactional
    public void deleteById(int id){
        Organization organization = em.find(Organization.class, id);
        if(organization != null) em.remove(organization);
    }
}