/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.model.Account;
import com.rest.model.Customer;
import com.rest.model.Response;
import com.rest.repository.AccountRepository;
import com.rest.repository.CustomerRepository;
import com.rest.utils.AbstractUtils;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
@RequestMapping("/customer")
public class CustomerController extends AbstractUtils {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AuthController authController;

    public CustomerController(CustomerRepository customerRepository, AccountRepository accountRepository, AuthController authController) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.authController = authController;
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity login(@RequestBody Customer login) {
        Customer customer = customerRepository.findById(login.getId()).orElse(null);
        if (customer != null && Objects.equals(customer.getPin(), login.getPin())) {
            String token = authController.generateToke(customer.getId(), customer.getPin());
            return ResponseEntity.status(HttpStatus.OK).body(new Response(0, "Login successful. Your token is: " + token));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Response(100, "Invalid Login credentials."));
        }
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity register(@RequestBody Customer customer) {
        int pin = generateRandomPin();
        String date = currentDateTime("yyyy-MM-dd HH:mm:ss");
        customer.setPin(pin);
        customer.setRegistrationDate(date);

        Customer reg = customerRepository.save(customer);
        Account acc = new Account(0L, reg.getId(), 0L);
        accountRepository.save(acc);
        return ResponseEntity.status(HttpStatus.CREATED).body(reg);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity fetchAll() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    private Integer generateRandomPin() {
        int min = 1000;
        int max = 9999;
        int pin = (int) ((Math.random() * (max - min)) + min);
        return pin;
    }

}