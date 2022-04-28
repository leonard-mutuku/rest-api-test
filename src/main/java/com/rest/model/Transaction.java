/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.model;

import java.io.Serializable;
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
@Table(name = "transactions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Transaction implements Serializable {
    
    private static final long serialVersionUID = 6560053337492040659L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String type;
    private Long TransactionFrom;
    private Long TransactionTo;
    private Long amount;
    private String transactionDate;
    
}
