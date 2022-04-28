/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author leonard
 */
public abstract class AbstractUtils {
    
    public String currentDateTime(String fmrt) {
        SimpleDateFormat f = new SimpleDateFormat(fmrt);
        f.setTimeZone(TimeZone.getTimeZone("EAT"));
        java.util.Date d = new java.util.Date();
        String sd = f.format(d);

        return sd;
    }
    
}
