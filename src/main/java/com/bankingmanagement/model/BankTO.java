package com.bankingmanagement.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BankTO {
    private String BankName;
    private String BankAddress;
    private List<BranchTO> branches;
}
