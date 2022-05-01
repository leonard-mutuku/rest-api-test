/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author leonard
 */
@Entity
@Table(name = "account")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Account implements Serializable {
    
    private static final long serialVersionUID = -3923109807522539875L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Long customerId;
    private BigDecimal customerBalance;
    
    public Account(Long customerId, BigDecimal customerBalance) {
        this.customerId = customerId;
        this.customerBalance = customerBalance;
    }
    
}
