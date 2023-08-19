package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

@Component
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
