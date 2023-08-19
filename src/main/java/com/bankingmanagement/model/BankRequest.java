package com.bankingmanagement.model;

import com.bankingmanagement.entity.Branch;
import lombok.Data;

import java.util.List;

@Data
public class BankRequest {

//    private int bankCode;
    private String bankName;
    private String bankAddress;
    private List<BranchRequest> branchRequests;

}
