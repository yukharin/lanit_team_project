package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.ActionDAO;
import com.lanit.lkz_project.entities.Action;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionDAO actionDAO;


    @Transactional
    public void addAction(@NonNull Action action) {
        actionDAO.addAction(action);
    }

    @Transactional
    public void updateAction(@NonNull Action action) {
        actionDAO.updateAction(action);
    }

    @Transactional
    public void removeAction(@NonNull Action action) {
        actionDAO.removeAction(action);
    }

    @Transactional
    public void removeAction(long id) {
        actionDAO.removeAction(id);
    }

    @Transactional
    public Action getAction(long id) {
        return actionDAO.getAction(id);
    }

    @Transactional
    public List<Action> actions() {
        return actionDAO.actions();
    }


}
