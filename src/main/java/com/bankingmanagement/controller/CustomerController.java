package com.bankingmanagement.controller;

import com.bankingmanagement.exceptions.CustomerDetailsNotFound;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    List<CustomerTO> customerTOS=null;
    @GetMapping
    public ResponseEntity<List<CustomerTO>> getCustomers(){
        log.info("Inside the Customers controller.getCustomers");
        try{
            customerTOS = customerService.getAllCustomers();
            log.info("get all customers:{}",customerTOS);
        }catch (CustomerDetailsNotFound cdnf){
            log.error("Customer details not found", cdnf);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex1){
            log.error("Kuch gadbad hai",ex1);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity<>(customerTOS,HttpStatus.OK);
    }
}
