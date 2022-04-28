/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.model.Transaction;
import com.rest.repository.TransactionRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity fetchAll(@PathVariable(name = "id") Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity fetchAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }
    
}
