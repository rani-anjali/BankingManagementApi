package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.exceptions.AccountNotFound;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountTO> getAllAccounts() throws AccountNotFound {

        log.info("Inside the accountServiceImpl.getAllAccount");
        List<Account> accounts = accountRepository.findAll();
        log.info("List of accounts:{}",accounts);
        if(CollectionUtils.isEmpty(accounts)){
            log.info("No account found");
            throw new AccountNotFound("no account found");
        }
        List<AccountTO> accountTOS=accounts.stream().map(account -> {
            AccountTO accountTO = new AccountTO();
            accountTO.setAccBalance(account.getAccountBalance());
            accountTO.setAccType(account.getAccountType());
            log.info("getting accounts details");

            return accountTO;
        }).collect(Collectors.toList());
        log.info("End of Account.serviceImpl.findAllAccounts");
        return accountTOS;

    }
}
