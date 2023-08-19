package com.bankingmanagement.service;

import com.bankingmanagement.exceptions.AccountNotFound;

import com.bankingmanagement.model.AccountTO;

import java.util.List;

public interface AccountService {
    public List<AccountTO> getAllAccounts() throws AccountNotFound;
}
