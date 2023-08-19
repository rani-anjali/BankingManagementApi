package com.bankingmanagement.controller;

import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.exceptions.BranchNotFound;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/branch")
public class BranchController {
    @Autowired
    BranchService branchService;
    BranchTO branchTO=null;
    @GetMapping("/{branchCode}")
    public ResponseEntity<BranchTO> getBranchById(@PathVariable("branchCode") int branchCode){
        log.info("Inside of Branch Controller");
        try{
            branchTO=branchService.getAllBranchById(branchCode);
        }catch(BranchNotFound bnf){
            log.error("No Branch Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("branchById controller khatm!!");
        return new ResponseEntity<>(branchTO, HttpStatus.OK);
    }
    @GetMapping("/branchName")
    public ResponseEntity<BranchTO> getBranchByName(@RequestParam("branchName") String branchName){
        log.info("Inside of Branch Controller");
        try{
            branchTO=branchService.getAllBranchByName(branchName);
        }catch(BranchNotFound bnf){
            log.error("No Branch Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("branchByName controller khatm!!");
        return new ResponseEntity<>(branchTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchTO> saveBranch(@RequestBody BranchRequest branchRequest)
    {
        log.info("Inside of Branch controller.saveBranch:{}",branchRequest);
        System.out.println(branchRequest);
        if(branchRequest==null){
            log.info("Invalid bank request:{}",branchRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BranchTO branchTO1 = null;
        try{
            branchTO1=branchService.saveBranch(branchRequest);
            log.info("branch save ho gya");
        }catch (BranchNotFound bdnf){
            log.error("Branch details not found", bdnf);
        }catch(Exception ex1){
            log.error("Exception while getting branch details");
        }
        log.info("End of Post request");
        return new ResponseEntity<>(branchTO1, HttpStatus.OK);
    }
}
