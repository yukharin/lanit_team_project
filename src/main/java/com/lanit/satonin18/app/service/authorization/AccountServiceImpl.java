package com.lanit.satonin18.app.service.authorization;


import com.lanit.satonin18.app.entity.authorization.Account;
import com.lanit.satonin18.app.repository.authorization.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

   @Autowired
   private AccountRepository accountRepository;

   @Override
   public void save(Account account) {
      accountRepository.save(account);
   }

   @Override
   public void delete(Account account) {
      accountRepository.delete(account);
   }

   @Override
   public Account findById(int id) {
      return accountRepository.findById(id).get();
   }

   @Override
   public List<Account> findAll() {
      return accountRepository.findAll();
   }

   @Override
   public void deleteById(int id) {
      accountRepository.deleteById(id);
   }

   @Override
   public Account findByUsername(String username) {
      return accountRepository.findByUsername(username);
   }
}