package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Loan implements Serializable {
    @Id
//    @Column(name="")
    private int Loan_id;
    private String Loan_type;
    private int loan_amount;

    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;
//    @ManyToOne
//    @JoinColumn(name="cust_id")
//    private Customer customer;
}
