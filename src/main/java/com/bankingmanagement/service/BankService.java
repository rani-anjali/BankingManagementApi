package com.bankingmanagement.service;

import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BankTO;

import java.util.List;

public interface BankService {
    public List<BankTO> getAllBanks() throws BankDetailsNotFound;

    BankTO getBankById(int bankCode) throws BankDetailsNotFound;

    BankTO getBankByName(String bankName)throws BankDetailsNotFound;
    BankTO saveBank(BankRequest bankRequest)throws BankDetailsNotFound;

    BankTO updateBank(BankRequest bankRequest)throws BankDetailsNotFound;

    String deleteByBankCode(int bankCode) throws BankDetailsNotFound;

    String deleteByBankName(String bankName);


}
