package com.lanit.satonin18.app.service.entities_service.authorization;


import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.repository.authorization.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

   @Autowired
   private UserAccountRepository userAccountRepository;

   @Override
   @Transactional
   public void save(UserAccount userAccount) {
      userAccountRepository.save(userAccount);
   }

   @Override
   @Transactional
   public void delete(UserAccount userAccount) {
      userAccountRepository.delete(userAccount);
   }

   @Override
   public UserAccount findById(int id) {
      return userAccountRepository.findById(id).get();
   }

   @Override
   public List<UserAccount> findAll() {
      return userAccountRepository.findAll();
   }

   @Override
   @Transactional
   public void deleteById(int id) {
      userAccountRepository.deleteById(id);
   }

   @Override
   public UserAccount findByUsername(String username) {
      return userAccountRepository.findByUsername(username);
   }
}