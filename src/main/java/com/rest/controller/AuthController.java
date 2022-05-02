/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.controller;

import com.rest.security.JWTUtility;
import com.rest.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leonard
 */
@RestController
public class AuthController {
    
    @Autowired
    private JWTUtility jWTUtility;
    
    @Autowired
    private MyUserDetails myUserDetails;
    
    public  String generateToken(Long id, Integer pin) {
        String usrnm = String.valueOf(id) + pin;
        UserDetails ud = myUserDetails.loadUserByUsername(usrnm);
        String token = jWTUtility.generateToken(ud);
        return token;
    }
}
