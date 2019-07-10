package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.ActionTypeDAO;
import com.lanit.lkz_project.entities.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionTypeService {

    @Autowired
    private ActionTypeDAO actionTypeDAO;


    @Transactional
    public ActionType getActionType(long id) {
        return actionTypeDAO.getActionType(id);
    }

    @Transactional
    public List<ActionType> actionTypes() {
        return actionTypeDAO.actionTypeList();
    }


}
