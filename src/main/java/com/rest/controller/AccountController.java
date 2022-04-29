/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.model.Account;
import com.rest.model.CreditDebitDto;
import com.rest.model.Response;
import com.rest.model.Transaction;
import com.rest.model.TransferDto;
import com.rest.repository.AccountRepository;
import com.rest.repository.TransactionRepository;
import com.rest.utils.AbstractUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
@RequestMapping("/account")
public class AccountController extends AbstractUtils {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    @Autowired
    public AccountController(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    
    @GetMapping(path = "balance/{customer_id}", produces = "application/json")
    public ResponseEntity fetchBalance(@PathVariable(name = "customer_id") Long customer_id) {
        Account account = accountRepository.findByCustomerId(customer_id);
        Response response;
        if (account != null) {
            String balance = "Your Account balance is Ksh "+account.getCustomerBalance();
            response = new Response(0, balance);
        } else {
            response = new Response(100, "Sorry! Account Not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping(path = "/credit", consumes = "application/json")
    public ResponseEntity creditAccount(@RequestBody CreditDebitDto creditDto) {
        Long customer_id = creditDto.getCustomerId();
        Long credit_amount = creditDto.getAmount();
        Account account = accountRepository.findByCustomerId(customer_id);
        Response response;
        if (account != null) {
            String transactionDate = currentDateTime("yyyy-MM-dd HH:mm:ss");
            Transaction transaction = new Transaction(0L, "Credit", 0L, customer_id, credit_amount, transactionDate);
            transactionRepository.save(transaction);
            
            Long new_balance = credit_amount + account.getCustomerBalance();
            account.setCustomerBalance(new_balance);
            accountRepository.save(account);
            
            String msg = "Ksh "+credit_amount+" credited to your account. Your new balance is Ksh "+new_balance;
            response = new Response(0, msg);
        } else {
            response = new Response(100, "Sorry! Account to credit Not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping(path = "/debit", consumes = "application/json")
    public ResponseEntity debitAccount(@RequestBody CreditDebitDto creditDto) {
        Long customer_id = creditDto.getCustomerId();
        Long debit_amount = creditDto.getAmount();
        Account account = accountRepository.findByCustomerId(customer_id);        
        Response response;
        if (account != null) {
            Long account_balance = account.getCustomerBalance();
            if (account_balance >= debit_amount) {
                String transactionDate = currentDateTime("yyyy-MM-dd HH:mm:ss");
                Transaction transaction = new Transaction(0L, "Debit", customer_id, 0L, debit_amount, transactionDate);
                transactionRepository.save(transaction);

                Long new_balance = account_balance - debit_amount;
                account.setCustomerBalance(new_balance);
                accountRepository.save(account);

                String msg = "Ksh "+debit_amount+" debited from your account. Your new balance is Ksh "+new_balance;
                response = new Response(0, msg);
            } else {
                String msg = "Sorry! Insufficient funds. Ksh "+ (debit_amount - account_balance) +" more needed to perform transaction.";
                response = new Response(105, msg);
            }            
        } else {
            response = new Response(100, "Sorry! Account to debit Not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping(path = "/transfer", consumes = "application/json")
    public ResponseEntity transferFunds(@RequestBody TransferDto transferDto) {
        Response response;
        Long source_id = transferDto.getSource();
        Long destination_id = transferDto.getDestination();
        Long transfer_amount = transferDto.getAmount();
        
        Account source_acc = accountRepository.findByCustomerId(source_id);
        Account destination_acc = accountRepository.findByCustomerId(destination_id);
        
        if (source_acc == null) {
            response = new Response(100, "Sorry! Account to debit Not found.");
        } else if (destination_acc == null) {
            response = new Response(100, "Sorry! Account to credit Not found.");
        } else {
            Long source_balance = source_acc.getCustomerBalance();
            if (source_balance >= transfer_amount) {
                String transactionDate = currentDateTime("yyyy-MM-dd HH:mm:ss");
                Transaction transaction = new Transaction(0L, "Transfer", source_id, destination_id, transfer_amount, transactionDate);
                transactionRepository.save(transaction);

                Long new_balance = source_balance - transfer_amount;
                source_acc.setCustomerBalance(new_balance);
                accountRepository.save(source_acc);
                
                Long destination_balance = destination_acc.getCustomerBalance();
                destination_acc.setCustomerBalance(destination_balance + transfer_amount);
                accountRepository.save(destination_acc);

                String msg = "Ksh "+transfer_amount+" debited from your account. Your new balance is Ksh "+new_balance;
                response = new Response(0, msg);
            } else {
                String msg = "Sorry! Insufficient funds. Ksh "+ (transfer_amount - source_balance) +" more needed to perform transaction.";
                response = new Response(105, msg);
            }
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
