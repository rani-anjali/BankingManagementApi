package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.repository.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankServiceImpl bankServiceImpl;

    @Test
    public void testGetAllBanks() throws BankDetailsNotFound{
        Bank bank=new Bank();//Entity
        bank.setBank_code(999);
        bank.setBank_name("SBI");
        bank.setBank_address("Mumbai");
        Bank bank1=new Bank();
        bank.setBank_code(1000);
        bank.setBank_name("HDFC");
        bank.setBank_address("Mumbai");
        List<Bank> banks=new ArrayList<>();
        banks.add(bank);
        banks.add(bank1);

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS=bankServiceImpl.getAllBanks();
        assertEquals(2,bankTOS.size());

    }
    @Test(expected=BankDetailsNotFound.class)
    public void testGetAllBanks_EmptyBank() throws BankDetailsNotFound{
        List<Bank> banks=null;


        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS=bankServiceImpl.getAllBanks();
        assertNull(bankTOS);

    }
    @Test
    public void testGetAllBanksWithBranch() throws BankDetailsNotFound{
        List<Bank> banks=new ArrayList<>();
        Bank bank=new Bank();
        bank.setBank_code(999);
        bank.setBank_name("SBI");
        bank.setBank_address("Mumbai");
        Set<Branch> branchSet=new HashSet<>();
        Branch branch=new Branch();
        branch.setBranch_name("Ecity");
        branch.setBranch_id(122345);
        branchSet.add(branch);
        bank.setBranchSet(branchSet);

        banks.add(bank);


        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS=bankServiceImpl.getAllBanks();
        assertEquals(1,bankTOS.size());

    }

    @Test
    public void testGetAllBanksById() throws BankDetailsNotFound{

        Bank bank=new Bank();
        bank.setBank_code(999);
        bank.setBank_name("SBI");
        bank.setBank_address("Mumbai");

        BankTO bank1=new BankTO();

        bank1.setBankName(bank.getBank_name());
        bank1.setBankAddress(bank.getBank_address());


        when(bankRepository.findById(anyInt())).thenReturn(Optional.of(bank));
        BankTO bankTO=bankServiceImpl.getBankById(999);


        assertEquals(bank1.getBankName(),bankTO.getBankName());

    }
    @Test
    public void testGetAllBanksByName() throws BankDetailsNotFound {
        Bank bank = new Bank();
        bank.setBank_code(999);
        bank.setBank_name("SBI");
        bank.setBank_address("Mumbai");

        BankTO bank1=new BankTO();

        bank1.setBankName(bank.getBank_name());
        bank1.setBankAddress(bank.getBank_address());



        when(bankRepository.findByBankName(anyString())).thenReturn(Optional.of(bank));

        BankTO bankTO=bankServiceImpl.getBankByName("SBI");
        assertEquals(bank1.getBankName(),bankTO.getBankName());
    }
//    @Test
//    public void testSaveBank() throws BankDetailsNotFound {
//        Bank bank =new Bank();
//        bank.setBank_code(88);
//        bank.setBank_name("SBI");
//        bank.setBank_address("Ecity");
//
//        Set<Branch> branchSet=new HashSet<>();
//        Branch branch=new Branch();
//        branch.setBranch_name("Ecity-2");
//        branch.setBranch_id(122345);
//        branchSet.add(branch);
//        bank.setBranchSet(branchSet);
//
//        BankRequest bankRequest=new BankRequest();
//        bankRequest.setBankName("SBI");
//        bankRequest.setBankAddress("Ecity");
////
//        List<BranchRequest> branchRequests=new ArrayList<>();
//        BranchRequest branchRequest=new BranchRequest();
//        branchRequest.setBranchName("Ecity-2");
//        branchRequest.setBranchAddress("Ecity-1");
//        branchRequests.add(branchRequest);
//
//
//        when(bankRepository.save(any())).thenReturn(bank);
//
//        BankTO bankTO=bankServiceImpl.saveBank(bankRequest);




//    }


}
