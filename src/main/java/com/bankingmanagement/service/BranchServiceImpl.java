package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.exceptions.BranchNotFound;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BranchServiceImpl implements BranchService{
    @Autowired
    BranchRepository branchRepository;


    @Override
    public BranchTO getAllBranchById(int branchCode) throws BranchNotFound {
        log.info("Inside the branchimpls.getAllBranch");
        Optional<Branch> branch=branchRepository.findById(branchCode);
        log.info("optional kaam kr rha hai",branch.get());
        if(!branch.isPresent()){
            log.info("No branch found",branchCode);
            throw new BranchNotFound("No branch found");
        }
        Branch branch1 = branch.get();
        BranchTO branchTO=new BranchTO();
        branchTO.setBranchName(branch1.getBranch_name());
        branchTO.setBranchAddress(branch1.getBranch_address());
        log.info("Branchservice Impls khatm!!");
        return branchTO;
    }

    @Override
    public BranchTO getAllBranchByName(String branchName) throws BranchNotFound {
        log.info("Inside getBranchByName");
        Optional<Branch> branch = branchRepository.findByBranchName(branchName);
        log.info("Optinal k baad branch ka data aa rha",branch.get());
        if(branch.isEmpty())
        {
            log.info("Bank details not found for bankName",branchName);
            throw new BranchNotFound("Branch not found for this name");
        }
        Branch branch1=branch.get();
        BranchTO branchTO=new BranchTO();
        branchTO.setBranchName(branch1.getBranch_name());
        branchTO.setBranchAddress(branch1.getBranch_address());
        return branchTO;
    }

    @Override
    public BranchTO saveBranch(BranchRequest branchRequest) throws BranchNotFound {
        log.info("Inside the BankServiceImpl.saveBank, bankRequest:{}",branchRequest);
        Branch branch=new Branch();
        branch.setBranch_name(branchRequest.getBranchName());
        branch.setBranch_address(branchRequest.getBranchAddress());
        Branch branchResponse=branchRepository.save(branch);
        log.info("saved branch is:{}",branchResponse);
        if(branchResponse == null){
            log.info("Bank details are not saved");
            throw new BranchNotFound("Branch details not found");
        }
        BranchTO branchTO=new BranchTO();
        branchTO.setBranchName(branchResponse.getBranch_name());
        branchTO.setBranchAddress(branchResponse.getBranch_address());
        return branchTO;
    }

}
