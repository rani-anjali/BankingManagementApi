package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl implements BankService{

    @Autowired
    private BankRepository bankRepository;
    @Override
    public List<BankTO> getAllBanks() throws BankDetailsNotFound{

        log.info("Inside the bankServiceimpl.getAllBanks");
        List<Bank> banks=bankRepository.findAll();
        log.info("List of banks:{}",banks);
        if(CollectionUtils.isEmpty(banks))
        {
            log.info("Banks details not found");
            throw new BankDetailsNotFound("Banks details not found");
        }
        List<BankTO> bankTOS = banks.stream().map(bank->{
            BankTO bankto=new BankTO();
            bankto.setBankName(bank.getBank_name());
            bankto.setBankAddress(bank.getBank_address());
            log.info("banks is retreving");

            Set<Branch> branches=bank.getBranchSet();
            if(!CollectionUtils.isEmpty(branches)){
                log.info("branches is retreving");
                List<BranchTO> branchTOS = branches.stream().map(branch -> {
                    BranchTO branchTO=new BranchTO();
                    branchTO.setBranchName(branch.getBranch_name());
                    branchTO.setBranchAddress(branch.getBranch_address());
                    return branchTO;
                }).collect(Collectors.toList());
                bankto.setBranches(branchTOS);
            }
            return bankto;
        }).collect(Collectors.toList());
        return bankTOS;
    }

//    Get the bank details from bank code
//    @Param bankcode
    @Override
    public BankTO getBankById(int bankCode) throws BankDetailsNotFound {
        log.info("Inside the controller of Bank by id:{}",bankCode);
        Optional<Bank> bank = bankRepository.findById(bankCode);
        log.info("Optional k baad data aa rha hai",bank.get());

        if(!bank.isPresent()){
            log.info("Bank details not found for bankCode",bankCode);
            throw new BankDetailsNotFound("Banks not found for this code");
        }
        Bank bank1= bank.get();
        BankTO bankTO=new BankTO();
        bankTO.setBankName(bank1.getBank_name());
        bankTO.setBankAddress(bank1.getBank_address());

        Set<Branch> branches=bank1.getBranchSet();
        if(!CollectionUtils.isEmpty(branches)){
            log.info("branches is retreving");
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO=new BranchTO();
                branchTO.setBranchName(branch.getBranch_name());
                branchTO.setBranchAddress(branch.getBranch_address());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO.setBranches(branchTOS);
        }
        log.info("End of bankserviceimpl.getById");
        return bankTO;
    }

    @Override
    public BankTO getBankByName(String bankName) throws BankDetailsNotFound {
        log.info("Inside GetBankByName Controller");
        Optional<Bank> bank=bankRepository.findByBankName(bankName);
        log.info("Optional k baad data aa rha hai",bank.get());

        if(!bank.isPresent()){
            log.info("Bank details not found for bankName",bankName);
            throw new BankDetailsNotFound("Banks not found for this code");
        }
        Bank bank1= bank.get();
        BankTO bankTO=new BankTO();
        bankTO.setBankName(bank1.getBank_name());
        bankTO.setBankAddress(bank1.getBank_address());

        Set<Branch> branches=bank1.getBranchSet();
        if(!CollectionUtils.isEmpty(branches)){
            log.info("branches is retreving");
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO=new BranchTO();
                branchTO.setBranchName(branch.getBranch_name());
                branchTO.setBranchAddress(branch.getBranch_address());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO.setBranches(branchTOS);
        }
        log.info("End of bankserviceimpl.getById");
        return bankTO;
    }

    @Override
    public BankTO saveBank(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.saveBank, bankRequest:{}",bankRequest);
        Bank bank=new Bank();
        bank.setBank_name(bankRequest.getBankName());
        bank.setBank_address(bankRequest.getBankAddress());

        if(!CollectionUtils.isEmpty(bankRequest.getBranchRequests())){
            Set<Branch> branches=new HashSet<>();
            bankRequest.getBranchRequests().forEach(data-> {
                Branch branch = new Branch();
                branch.setBranch_name(data.getBranchName());
                branch.setBranch_address(data.getBranchAddress());
                branches.add(branch);
                log.info("Branches added:{}",branches);
            });
            bank.setBranchSet(branches);
        }
        Bank bankResponse=bankRepository.save(bank);
        if(bankResponse == null){
            log.info("Bank details are not saved");
            throw new BankDetailsNotFound("Bank details not found");
        }
        BankTO bankTO=new BankTO();
        bankTO.setBankName(bankResponse.getBank_name());
        bankTO.setBankAddress(bankResponse.getBank_address());
        System.out.println(bankResponse);
        Set<Branch> branches=bankResponse.getBranchSet();
        if(!CollectionUtils.isEmpty(branches)){
            log.info("branches is retreving");
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO=new BranchTO();
                branchTO.setBranchName(branch.getBranch_name());
                branchTO.setBranchAddress(branch.getBranch_address());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO.setBranches(branchTOS);
        }
        log.info("End of bankserviceimpl.Post is working");
        return bankTO;
    }

    @Override
    public BankTO updateBank(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.saveBank, bankRequest:{}",bankRequest);
        Bank bank=new Bank();
        bank.setBank_name(bankRequest.getBankName());
        bank.setBank_address(bankRequest.getBankAddress());
        Bank bankResponse=bankRepository.save(bank);
        if(bankResponse == null){
            log.info("Bank details are not saved");
            throw new BankDetailsNotFound("Bank details not found");
        }
        BankTO bankTO=new BankTO();
        bankTO.setBankName(bankResponse.getBank_name());
        bankTO.setBankAddress(bankResponse.getBank_address());
        System.out.println(bankResponse);
        Set<Branch> branches=bankResponse.getBranchSet();
        if(!CollectionUtils.isEmpty(branches)){
            log.info("branches is retreving");
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO=new BranchTO();
                branchTO.setBranchName(branch.getBranch_name());
                branchTO.setBranchAddress(branch.getBranch_address());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO.setBranches(branchTOS);
        }
        log.info("End of bankserviceimpl.Post is working");
        return bankTO;

    }

    @Override
    public String deleteByBankCode(int bankCode) throws BankDetailsNotFound {
        log.info("Inside the BankServiceImpl.deleteBankByCode, bankCode:{}", bankCode);
        Optional<Bank> bankOptional=bankRepository.findById(bankCode);
        if(bankOptional.isEmpty()){
            log.info("Bank details doest not exist");
            throw new BankDetailsNotFound("Bank details does not exist");
        }
        bankRepository.delete(bankOptional.get());
        log.info("Bank has been deleted by bank code");
        return "Bank has been deleted by bank code";
    }

    @Override
    public String deleteByBankName(String bankName) {
        log.info("Inside the BankServiceImpl.deleteBankByName:{}",bankName);
        Optional<Bank> bankOptional=bankRepository.findByBankName(bankName);
        if(bankOptional.isEmpty()){
            log.info("Bank details doest not exist");
//            throw new BankDetailsNotFound("Bank details does not exist");
        }
        bankRepository.delete(bankOptional.get());
        log.info("Bank has been deleted by bank name");
        return "Bank has been deleted by bank name";
    }


}
