package com.bankingmanagement.exceptions;

public class BankDetailsNotFound extends Exception{
    public BankDetailsNotFound(){
        super();
    }
    public BankDetailsNotFound(String str){
        super(str);
    }
}
