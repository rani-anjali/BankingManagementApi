package com.bankingmanagement.controller;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.exceptions.AccountNotFound;
import com.bankingmanagement.model.AccountTO;
import com.bankingmanagement.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    List<AccountTO> accountTOS = null;

    @GetMapping
    public ResponseEntity<List<AccountTO>> getAccounts(){
        log.info("Inside Account Controller.getAccounts");
        try{
            accountTOS = accountService.getAllAccounts();
            log.info("List of Banks, banks:{}", accountTOS);

        }catch(AccountNotFound acnf){
            log.error("Account not found", acnf);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("End of AccountController.getBanks");
        return new ResponseEntity<>(accountTOS,HttpStatus.OK);
    }
}
