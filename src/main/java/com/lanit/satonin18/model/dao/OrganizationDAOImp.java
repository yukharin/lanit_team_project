package com.lanit.satonin18.model.dao;

import com.lanit.satonin18.model.entity.Organization;
import com.lanit.satonin18.model.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

@Repository("organizationDAO")
public class OrganizationDAOImp implements OrganizationDAO {

    //@Component
    //@Inject
    //@Resource(name="sessionFactory")
    //@Qualifier("sessionFactory")
    //@Named("sessionFactory")
    //add bean with info hibernate
    //@Autowired
    //private SessionFactory sessionFactory;
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public void addOrganization(Organization organization) {  //TODO need add @NotNull final IN ARG //throws Exc
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.saveOrUpdate(organization);

            tx1.commit();
        }
    }

    @Override
    public void updateOrganization(Organization organization) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            session.update(organization);

            tx1.commit();
        }
    }
/*
    @Override
    public List<Organization> searchOrganizations(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;
        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Organization where name like :theName", Organization.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Organization", Organization.class);
        }
        List<Organization> organizations = theQuery.getResultList();
        return organizations;
    }
*/
    @Override
    public void removeOrganization(int id) {
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
    public Organization getOrganizationById(int id) {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            Organization organization = session.get(Organization.class, id);

            tx1.commit();
            session.close();
            return organization;
        }
    }

    @Override
    public List<Organization> organizations() {
        //Session session = sessionFactory.getCurrentSession();
        try(final Session session = sessionFactory.openSession();){
            Transaction tx1 = session.beginTransaction();

            List<Organization> organizations = session.createQuery("from Organization order by name", Organization.class).list();

            tx1.commit();
            return organizations;
        }
    }
}