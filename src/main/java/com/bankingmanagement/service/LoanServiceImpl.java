package com.bankingmanagement.service;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exceptions.NoLoansExist;
import com.bankingmanagement.model.LoanTO;
import com.bankingmanagement.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService{
    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanTO> getAllLoans() throws NoLoansExist {
        log.info("Inside the loanServiceimpl");
            List<Loan> loans=loanRepository.findAll();
            log.info("List of banks:{}",loans);
//            List<LoanTO> loanTOS=null;
        if(CollectionUtils.isEmpty(loans)){
                log.info("Loans not found");
                throw new NoLoansExist("NO LOANS Found");
            }
        List<LoanTO> loanTOS=loans.stream().map(loan->{
                LoanTO loanTO=new LoanTO();
                loanTO.setLoanType(loan.getLoan_type());
//                loanTO.setLoan_type(loan.getLoan_type());
                loanTO.setLoanAmount(loan.getLoan_amount());
                return loanTO;
            }).collect(Collectors.toList());
        return loanTOS;
    }


////    public List<LoanTO> getAllLoans() throws NoLoansExist {
//
//            log.info("Inside the loanServiceimpl");
//            List<LoanTO> loans=loanRepository.findAll();
//            log.info("List of banks:{}",loans);
//
//            if(CollectionUtils.isEmpty(loans)){
//                log.info("Loans not found");
//                throw new NoLoansExist("NO LOANS Found");
//            }
//            List<Loan> loanTOS=loans.stream().map(loan->{
//                LoanTO loanTO=new LoanTO();
//                loanTO.setLoanType(loan.getLoanType());
//                loanTO.setLoanAmount(loan.getAmount());
//                return loanTO;
//            }).collect(Collectors.toList());
//
//        return LoanTOS;
//    }
}