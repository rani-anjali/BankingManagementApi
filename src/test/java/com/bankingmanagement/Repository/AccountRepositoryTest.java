package com.bankingmanagement.Repository;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindAll(){
        Account account=new Account();//entity
        account.setAccountType("saving");
        account.setAccountBalance(12000);
        accountRepository.save(account);

        List<Account> accountList=accountRepository.findAll();
        assertEquals(1,accountList.size());

    }

}
