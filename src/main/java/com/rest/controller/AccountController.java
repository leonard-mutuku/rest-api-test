/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.repository.AccountRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    
    private final AccountRepository accountRepository;
    
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
}
