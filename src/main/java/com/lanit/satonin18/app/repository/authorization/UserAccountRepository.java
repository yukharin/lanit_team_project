package com.lanit.satonin18.app.repository.authorization;

import com.lanit.satonin18.app.entity.authorization.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository
        extends JpaRepository<UserAccount, Integer> {

    UserAccount findByUsername(String username);
}
