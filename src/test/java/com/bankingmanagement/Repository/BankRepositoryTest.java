package com.bankingmanagement.Repository;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.repository.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankRepositoryTest {
    @Autowired
    private BankRepository bankRepository;
    @Test
    public void testFindAll()
    {

        Bank bank = new Bank();
        bank.setBank_code(50);
        bank.setBank_name("HDFC");
        bank.setBank_address("Hyderbad");
        List<Bank> bankList = bankRepository.findAll();
        assertEquals("",1,bankList.size());

    }

}
