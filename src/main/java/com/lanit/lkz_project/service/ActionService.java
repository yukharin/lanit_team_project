package com.lanit.lkz_project.service;

import com.lanit.lkz_project.dao.ActionDAO;
import com.lanit.lkz_project.entities.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionDAO actionDAO;


    @Transactional
    public void addAction(Action action) {
        actionDAO.addAction(action);
    }

    @Transactional
    public void updateAction(Action action) {
        actionDAO.updateAction(action);
    }

    @Transactional
    public void removeAction(Action action) {
        actionDAO.removeAction(action);
    }

    @Transactional
    public void removeAction(int id) {
        actionDAO.removeAction(id);
    }

    @Transactional
    public Action getAction(int id) {
        return actionDAO.getAction(id);
    }

    @Transactional
    public List<Action> actions() {
        return actionDAO.actions();
    }

}
