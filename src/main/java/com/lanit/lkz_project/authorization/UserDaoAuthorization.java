package com.lanit.lkz_project.authorization;

import com.lanit.lkz_project.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoAuthorization {

    @Autowired
    private SessionFactory sessionFactory;

    public User authorizeUser(String login, String password) {
        Session session = sessionFactory.getCurrentSession();
        // Очень костыльно и плохо сделано , но пока так
        return (User) session.createQuery("FROM User WHERE login=:login and password=:password")
                .setParameter("login", login).setParameter("password", password).list().get(0);
    }

}
