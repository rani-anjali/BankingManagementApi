package com.bankingmanagement.service;

import com.bankingmanagement.exceptions.BranchNotFound;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.model.BranchTO;

import java.util.List;

public interface BranchService {
    BranchTO getAllBranchById(int branchCode) throws BranchNotFound;
    BranchTO getAllBranchByName(String branchName)throws BranchNotFound;

    BranchTO saveBranch(BranchRequest branchRequest)throws BranchNotFound;
}
