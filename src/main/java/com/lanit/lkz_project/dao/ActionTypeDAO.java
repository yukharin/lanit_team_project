package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.ActionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActionTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void addActionType(ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(actionType);
    }

    public void updateActionType(ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(actionType);
    }

    public void removeActionType(ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(actionType);
    }

    public ActionType getActionType(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(ActionType.class, id);
    }

    public List<ActionType> actionTypeList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM ActionType ").list();
    }

}
