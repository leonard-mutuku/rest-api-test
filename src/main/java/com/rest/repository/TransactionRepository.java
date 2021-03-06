/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.repository;

import com.rest.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leonard
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Page<Transaction> findBySourceOrDestination(Long source, Long destination, Pageable pageable);
}
