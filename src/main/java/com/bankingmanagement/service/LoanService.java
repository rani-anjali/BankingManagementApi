package com.bankingmanagement.service;

import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exceptions.NoLoansExist;
import com.bankingmanagement.model.LoanTO;

import java.util.List;

public interface LoanService {
    public List<LoanTO> getAllLoans() throws NoLoansExist;
}
