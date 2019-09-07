package com.lanit.lkz_project.service.jpa_entities_service;

import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.repositories.entitity_repositories.ActionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;


    @Transactional
    public void addAction(@Valid @NonNull Action action) {
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

    @Transactional
    public List<Action> getActionsOfNotification(long id) {
        return actionRepository.findByNotificationId(id);
    }


}
