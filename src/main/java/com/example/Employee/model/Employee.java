package com.example.Employee.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="employee")
public class Employee {
    @Id
    private long employee_id;
    private String first_name;
    private String last_name;
    private String address;
//    @JoinColumn(name="address_id")
//    @OneToOne
//    private Address addressId;
}
