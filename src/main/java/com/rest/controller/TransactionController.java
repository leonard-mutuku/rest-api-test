/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.model.Response;
import com.rest.model.Transaction;
import com.rest.repository.TransactionRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity fetchOne(@PathVariable(name = "id") Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            Response response = new Response(100, "Sorry! Transaction Not found.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        }        
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity fetchAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Sort sort = Sort.by("transactionId").descending();
        int size = (int) Math.ceil(offset / limit);
        Pageable pageable = PageRequest.of(size, limit, sort);
        Page page = transactionRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("transactions", page.getContent());
        response.put("size", page.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping(path = "mini-statement/{customer_id}", produces = "application/json")
    public ResponseEntity fetchMiniStatement(@PathVariable(name = "customer_id") Long customer_id) {
        Sort sort = Sort.by("transactionId").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page page = transactionRepository.findBySourceOrDestination(customer_id, customer_id, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("transactions", page.getContent());
        response.put("size", page.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
