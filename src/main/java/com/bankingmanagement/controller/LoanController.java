package com.bankingmanagement.controller;

import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exceptions.NoLoansExist;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.service.LoanService;
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
@RequestMapping("api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    List<LoanTO> loanTOS=null;
    @GetMapping
    public ResponseEntity<List<LoanTO>> getAllLoans(){
        log.info("Inside loan Controller");
        try{
            loanTOS = loanService.getAllLoans();
            log.info("getting all loans:{}",loanTOS);
        }catch(NoLoansExist noe){
            log.error("No loans found", noe);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("End of loan controller.getLoans");
        return new ResponseEntity<>(loanTOS, HttpStatus.OK);
    }
}
