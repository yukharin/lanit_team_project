package com.lanit.lkz_project.authorization;

import com.lanit.lkz_project.entities.jpa_entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@Repository
public class UserAuthorization {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public User authorizeUser(String login, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Очень костыльно и плохо сделано , но пока так
        Query query = entityManager.createNativeQuery("SELECT * FROM users WHERE login= :login AND password= :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }

}
