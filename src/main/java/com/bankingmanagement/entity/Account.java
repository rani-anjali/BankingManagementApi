package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Account implements Serializable {
    public static final long serialVersionUID = 243243545l;
    @Id
    @Column(name="Acc_no")
    private int accountNumber;
    @Column(name="Acc_type")
    private String accountType;
    @Column(name="Acc_Balance")
    private int accountBalance;
    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "account")

    private Set<Customer> customer;

}
