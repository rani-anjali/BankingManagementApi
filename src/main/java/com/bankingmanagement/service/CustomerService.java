package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exceptions.CustomerDetailsNotFound;
import com.bankingmanagement.model.CustomerTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerTO> getAllCustomers() throws CustomerDetailsNotFound;
}
