package com.example.Employee.service;

import com.example.Employee.dao.EmployeeRespository;
import com.example.Employee.model.Address;
import com.example.Employee.model.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {

    @Autowired
    EmployeeRespository repository;
    public void addemployee(Employee employee)
    {
        repository.save(employee);
    }
    public JSONArray getEmployee(String employeeId)
    {
        JSONArray response=new JSONArray();
//        Optional<Employee> constants = repository.findById(1L);
//
//        Employee value = constants.orElse(null);
        if(employeeId!=null)
        {
            List<Employee> employeeList=repository.getByEmployeeId(Long.valueOf(employeeId));
            for(Employee emp:employeeList)
            {
                JSONObject employeeObj=createResponse(emp);
                response.put(employeeObj);
            }


        }
        else
        {
            List<Employee> employeeList=repository.findAll();
            for(Employee emp:employeeList)
            {
                JSONObject employeeObj=createResponse(emp);
                response.put(employeeObj);
            }
        }
        return  response;
    }

    public void deleteEmployee(int employeeId)
    {
        repository.deleteById((long)employeeId);
    }

    public Employee updateUser(Employee newEmployee, long employeeId)
    {
        List<Employee> usersList = repository.getByEmployeeId(employeeId);
        JSONObject obj = new JSONObject();
        Employee newAds=usersList.get(0);
        newAds.setEmployee_id(newEmployee.getEmployee_id());
        newAds.setFirst_name(newEmployee.getFirst_name());
        newAds.setLast_name(newEmployee.getLast_name());
        newAds.setAddress(newEmployee.getAddress());

        repository.save(newAds);
        return newAds;

    }

    public JSONObject createResponse(Employee employee)
    {
        JSONObject employeeObj=new JSONObject();
        employeeObj.put("employee_id",employee.getEmployee_id());
        employeeObj.put("last_name",employee.getLast_name());
        employeeObj.put("address",employee.getAddress());
        return employeeObj;

    }
}
