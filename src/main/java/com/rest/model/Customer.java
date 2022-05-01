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
@Table(name = "customers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Customer implements Serializable {
    
    private static final long serialVersionUID = -1687201491262802111L;
    
    @TableGenerator(name = "Customer_Gen", initialValue = 999)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Customer_Gen")
    private Long id;
    
    private String name;
    private Integer pin;
    private String registrationDate;
    
    public Customer(String name, Integer pin, String registrationDate) {
        this.name = name;
        this.pin = pin;
        this.registrationDate = registrationDate;
    }
    
}
