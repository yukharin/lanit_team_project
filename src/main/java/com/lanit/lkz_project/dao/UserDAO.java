package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }


    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }


    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (user != null)
            session.delete(user);
    }


    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }


    public List<User> users() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }
}
