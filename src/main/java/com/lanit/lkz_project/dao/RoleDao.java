package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {


    @Autowired
    private SessionFactory sessionFactory;


    public Role getRole(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

}
