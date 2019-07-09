package com.lanit.lkz_project.dao;

import com.lanit.lkz_project.entities.ActionType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class ActionTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;


    public void addActionType(@NotNull ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(actionType);
    }

    public void updateActionType(@NotNull ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(actionType);
    }

    public void removeActionType(@NotNull ActionType actionType) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(actionType);
    }

    public ActionType getActionType(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(ActionType.class, id);
    }

    public List<ActionType> actionTypeList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM ActionType ").list();
    }

}
