package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data

@Entity
@Table(name="branch")
public class Branch implements Serializable {
    public static final long serialVersionUID= 1878678757l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long branch_id;
    private String branch_name;
    private String branch_address;


    @ManyToOne
    @JoinColumn(name="bank_code")
    private Bank bank;

    @OneToMany(mappedBy = "branch")
    private Set<Loan> loansSet;

}
