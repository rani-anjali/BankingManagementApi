package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.Set;

@Data
//@Getter
//@Setter
@Entity
@Table(name="bank")
public class Bank implements Serializable {
    public static final long serialVersionUID = 4543545l;


//    @Column(name = "bank_name")
    private String bank_name;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "bank_code")
    private int bank_code;

//    @Column(name = "bank_Address")
    private String bank_address;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Branch> BranchSet;

}
//spring.jpa.hibernate.ddl-auto=update