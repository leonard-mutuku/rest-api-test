/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
    
    @TableGenerator(name = "Transaction_Gen", initialValue = 999)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Transaction_Gen")
    private Long id;
    
    private String type;
    private Long source;
    private Long destination;
    private Long amount;
    private String transactionDate;
    
}
