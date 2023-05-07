package com.example.Employee.dao;

import com.example.Employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRespository extends JpaRepository<Employee,Long> {

    @Query(value = "select * from employee where employee_id= :employeeId ",nativeQuery = true)
    public List<Employee> getByEmployeeId(long employeeId);
}
