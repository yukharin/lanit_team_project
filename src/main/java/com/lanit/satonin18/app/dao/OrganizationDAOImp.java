package com.lanit.satonin18.app.dao;


import com.lanit.satonin18.app.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("organizationDAO")
//@Transactional
public class OrganizationDAOImp implements CrudDAO<Organization> {

    //private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    //@Component
    //@Inject
    //@Resource(name="sessionFactory")
    //@Qualifier("sessionFactory")
    //@Named("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Organization organization) {  //TODO need saveOrUpdate @NotNull final IN ARG //throws Exc
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.saveOrUpdate(organization);

            tx1.commit();
        }
    }

    @Override
    public void update(Organization organization) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.update(organization);

            tx1.commit();
        }
    }

    @Override
    public void delete(int id) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Organization organization = session.load(Organization.class, id);

            if(organization != null)
                session.delete(organization);

            tx1.commit();
        }
    }

    @Override
    public Organization getById(int id) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
//            Transaction tx1 = session.beginTransaction();

            Organization organization = session.get(Organization.class, id);

//            tx1.commit();
            return organization;
        }
    }

    @Override
    public List<Organization> list() {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
//            Transaction tx1 = session.beginTransaction();

            List<Organization> organizations = session.createQuery("from Organization", Organization.class).list();

//            tx1.commit();
            return organizations;
        }
    }
}