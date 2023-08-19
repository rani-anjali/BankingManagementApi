package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

import java.io.Serializable;

@Data
@Entity
public class Customer implements Serializable {
    @Id
    @Column(name="cust_id")
    private int cust_id;
    @Column(name="cust_name")
    private String name;
    @Column(name="cust_phone")
    private long phone;
    @Column(name="cust_address")
    private String Address;

    @ManyToOne
    @JoinColumn(name="acc_no")
    private Account account;


}
