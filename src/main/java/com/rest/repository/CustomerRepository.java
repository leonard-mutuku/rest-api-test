/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.repository;

import com.rest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author leonard
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}