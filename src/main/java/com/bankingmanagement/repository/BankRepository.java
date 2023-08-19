package com.bankingmanagement.repository;
import com.bankingmanagement.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface BankRepository extends JpaRepository<Bank,Integer>{
    @Query(value = "select bank from Bank bank where bank_name=:bank_name")
    Optional<Bank> findByBankName(String bank_name);

}
