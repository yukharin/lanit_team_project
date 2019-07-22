package com.lanit.lkz_project.service.entities_service;

import com.lanit.lkz_project.dao.entities_dao.ActionRepository;
import com.lanit.lkz_project.entities.Action;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;


    @Transactional
    public void addAction(@NonNull Action action) {
        actionRepository.save(action);
    }

    @Transactional
    public void updateAction(@NonNull Action action) {
        actionRepository.save(action);
    }

    @Transactional
    public void removeAction(@NonNull Action action) {
        actionRepository.delete(action);
    }

    @Transactional
    public void removeAction(long id) {
        actionRepository.deleteById(id);
    }

    @Transactional
    public Action getAction(long id) {
        return actionRepository.getOne(id);
    }

    @Transactional
    public List<Action> actions() {
        return actionRepository.findAll();
    }


}
