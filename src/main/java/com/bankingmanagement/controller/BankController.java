package com.bankingmanagement.controller;

import com.bankingmanagement.exceptions.BankDetailsNotFound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BankTO;
import com.bankingmanagement.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/banks")
public class BankController {
    @Autowired
    private BankService bankService;
    List<BankTO> bankTOS=null;
    @GetMapping
    public ResponseEntity<List<BankTO>> getBanks(){
        log.info("Inside the BankController.getBanks");
        try{
            bankTOS = bankService.getAllBanks();
            log.info("List of Banks, banks:{}", bankTOS);

        }catch (BankDetailsNotFound bdnf){
            log.error("details not found", bdnf);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex1){
            log.error("kuch gadbad hai", ex1);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.getBanks");
        return new ResponseEntity<>(bankTOS,HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<BankTO> getBankById(@PathVariable("code") int bankCode){
        log.info("Inside bankController.getBankByName");
        BankTO bankTO=null;
        try{
            bankTO= bankService.getBankById(bankCode);
            log.info("GetById work kr rha hai!!");

        }catch(BankDetailsNotFound bankDetailsNotFound){
            log.error("Bank details not found", bankDetailsNotFound);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            log.error("Exception while getting the bank deatils by bank code ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("bankById controller khatm!!");
        return new ResponseEntity<>(bankTO, HttpStatus.OK);
    }

    @GetMapping("/bankName")
    public ResponseEntity<BankTO> getBankByName(@RequestParam("bankName") String bankName){
        log.info("Inside bankController.getBankByName");
        BankTO bankTO=null;
        if(StringUtils.isEmpty(bankName)){
            log.info("Invalid Input");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            bankTO= bankService.getBankByName(bankName);
            log.info("GetByName work kr rha hai!!");

        }catch(BankDetailsNotFound bankDetailsNotFound){
            log.error("Bank details not found", bankDetailsNotFound);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            log.error("Exception while getting the bank deatils by bank code ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("bankById controller khatm!!");
        return new ResponseEntity<>(bankTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BankTO> saveBank(@RequestBody BankRequest bankRequest){
        log.info("Inside the bank controller.saveBank:{}",bankRequest);
        System.out.println(bankRequest);
        if(bankRequest==null)
        {
            log.info("Invalid bank request:{}",bankRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankTO bankTO=null;
        try{
            bankTO=bankService.saveBank(bankRequest);
            log.info("bank save ho gya");
        }
        catch (BankDetailsNotFound bdnf){
            log.error("Banks details not found", bdnf);
        }catch(Exception ex1){
            log.error("Exception while getting bank details");
        }
        log.info("End of Post request");
        return new ResponseEntity<>(bankTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BankTO> updateBank(@RequestBody BankRequest bankRequest){
        log.info("Inside the Bank Controller for Update, bankRequest:{}",bankRequest);
        if(bankRequest==null){
            log.info("Invalid bank Request ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankTO bankTO=null;
        try{
            bankTO=bankService.updateBank(bankRequest);
            log.info("bank save ho gya");
        }
        catch (BankDetailsNotFound bdnf){
            log.error("Banks details not found", bdnf);
        }catch(Exception ex1){
            log.error("Exception while getting bank details");
        }
        log.info("End of Post request");
        return new ResponseEntity<>(bankTO, HttpStatus.OK);

    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteBank(@RequestParam("bankCode") int bankCode){
        log.info("Inside bank controllerv1.deleteBank:{}",bankCode);
        String response;
        if(bankCode<=0)
        {
            log.info("Invalid input");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            response=bankService.deleteByBankCode(bankCode);
            log.info("Bank has been deleted and we are in controller");

        }catch(Exception ex1){
            log.info("Exception while getting bank details by bankCode");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/bankName")
    public ResponseEntity<String> deleteBank(@RequestParam("bankName") String bankName){
        log.info("Inside Bank Controller in order to delete bank by bankname");
        String response;
        if(StringUtils.isEmpty(bankName)){
            log.info("invalid input");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            response=bankService.deleteByBankName(bankName);
            log.info("Bank has been deleted and we are in controller");
        }catch(Exception ex1){
            log.info("Exception while getting bank details by bankCode");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
