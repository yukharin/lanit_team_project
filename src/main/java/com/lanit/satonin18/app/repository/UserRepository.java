package com.lanit.satonin18.app.repository;

import com.lanit.satonin18.app.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer>
{
    public List<User> findByLastNameIgnoreCaseContaining(String lastName);
}