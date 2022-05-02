/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.utils;

import java.math.BigDecimal;

/**
 *
 * @author leonard
 */
public class Constants {
    
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final BigDecimal MIN_CREDIT_AMOUNT = new BigDecimal("1.00");
    public static final BigDecimal MIN_DEBIT_AMOUNT = new BigDecimal("1.00");
    public static final BigDecimal MIN_TRANSFER_AMOUNT = new BigDecimal("1.00");
    
    public static final String UNAUTHORISED_RESP = "Unauthorized request!";
    public static final long JWT_TOKEN_VALIDITY = 600000; // 10 minutes
    public static final String JWT_SECRET_KEY = "s9#Fk8UJk^@6x2BV";
    
}
