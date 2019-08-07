package com.lanit.satonin18.app.dao;

import com.lanit.satonin18.app.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("userDAO") //TODO need @NotNull final IN ARG //throws Exc
public class UserDAOImp implements UserDAO{
//    @Autowired
//    private SessionFactory sessionFactory;
//    protected Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    //   @Override
//   @Transactional
//   public void save(User user) {
//      sessionFactory.getCurrentSession()
//              .save(user);
//   }
//
    @Override
    @Transactional
    public void update(User user) {
        em.merge(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> list() {
        TypedQuery<User> typedQuery = em.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        User user = em.find(User.class, id);
        if(user != null) em.remove(user);
    }

    @Override
    public List<User> searchUserByLastName(String theSearchName) {
        TypedQuery theQuery = null;
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            theQuery = em.createQuery("from User where lastName like :theName", User.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            theQuery = em.createQuery("from User", User.class);
        }
        return theQuery.getResultList();
    }
}