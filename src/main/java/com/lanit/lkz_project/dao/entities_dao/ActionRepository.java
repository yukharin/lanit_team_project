package com.lanit.lkz_project.dao.entities_dao;

import com.lanit.lkz_project.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {


}
