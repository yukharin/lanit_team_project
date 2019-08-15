package com.lanit.satonin18.app.repository.authorization;

import com.lanit.satonin18.app.entity.User;
import com.lanit.satonin18.app.entity.authorization.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository
        extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);
}
