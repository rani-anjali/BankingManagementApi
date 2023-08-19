package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exceptions.CustomerDetailsNotFound;
import com.bankingmanagement.model.CustomerTO;
import com.bankingmanagement.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<CustomerTO> getAllCustomers() throws CustomerDetailsNotFound {
        log.info("Customer service is started working now!!!");
        List<Customer> customers=customerRepository.findAll();
        log.info("List of Customers:{}",customers);
        if(CollectionUtils.isEmpty(customers))
        {
            log.info("Customers not found");
            throw new CustomerDetailsNotFound();
        }
        List<CustomerTO> customerTOS=customers.stream().map(customer -> {
            CustomerTO customerTO=new CustomerTO();
            customerTO.setCustomerName(customer.getName());
            customerTO.setCustomerAddress(customer.getAddress());
            log.info("customer details are retreiving");
            return customerTO;
        }).collect(Collectors.toList());
        return customerTOS;
    }
}
