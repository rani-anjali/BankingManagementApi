package com.bankingmanagement.controller;

import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.model.BranchTO;
import com.bankingmanagement.service.BankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Test
    public void testGetAllBanks() throws Exception {
        List<BankTO> bankTOList = new ArrayList<>();
        BankTO bankTO = new BankTO();//Model
        bankTO.setBankName("SBI");
        bankTO.setBankAddress("Ecity");

        List<BranchTO> branchTOList=new ArrayList<>();
        BranchTO branchTO = new BranchTO();
        branchTO.setBranchName("Electronic city");
        branchTO.setBranchAddress("Electronic city");

        branchTOList.add(branchTO);
        bankTO.setBranches(branchTOList);

        when(bankService.getAllBanks()).thenReturn(bankTOList);

        RequestBuilder requsetBuilder = MockMvcRequestBuilders.get("/api/v1/banks")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requsetBuilder).andExpect(status().isOk());
    }
}
