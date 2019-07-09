package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void addUser(@NotNull User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }


    public void updateUser(@NotNull User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

    public void removeUser(@NotNull User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public void removeUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }


    public User getUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }


    public List<User> users() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User", User.class).list();
    }
}
