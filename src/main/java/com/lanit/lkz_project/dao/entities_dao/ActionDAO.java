package com.lanit.lkz_project.dao.entities_dao;

import com.lanit.lkz_project.entities.Action;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addAction(@NonNull Action action) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(action);
    }

    public void updateAction(@NonNull Action action) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(action);
    }

    public void removeAction(long id) {
        Session session = sessionFactory.getCurrentSession();
        Action action = session.load(Action.class, id);
        if (action != null) {
            session.delete(action);
        }
    }

    public void removeAction(@NonNull Action action) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(action);
    }

    public Action getAction(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Action.class, id);
    }

    public List<Action> actions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Action ORDER BY id ASC ").list();
    }


}
