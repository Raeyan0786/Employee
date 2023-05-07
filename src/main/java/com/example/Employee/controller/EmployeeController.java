package com.example.Employee.controller;

import com.example.Employee.model.Address;
import com.example.Employee.model.Employee;
import com.example.Employee.service.EmployeeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @PostMapping(value="/create-employee")
    public ResponseEntity<String> createEmployee(@RequestBody String employeeData)
    {
        JSONObject jsonData=new JSONObject(employeeData);
        Employee employee=setEmployee(jsonData);
        service.addemployee(employee);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    @GetMapping(value="/get-employee")
    public ResponseEntity<String> getEmployee(@Nullable @RequestParam String employeeId)
    {
        JSONArray employeeArray= service.getEmployee(employeeId);
        return new ResponseEntity<>(employeeArray.toString(),HttpStatus.OK);
    }


    @DeleteMapping("/delete-employee/{employeeId}")
    public ResponseEntity<String> deleteUser(@PathVariable int employeeId)
    {
        service.deleteEmployee(employeeId);
        return new ResponseEntity<>("delete",HttpStatus.OK);
    }

    @PutMapping("/update-employee/{employeeId}")
    public ResponseEntity<String> upadteAddress(@PathVariable long addressId,@RequestBody String requestData)
    {
        JSONObject jsonObj = new JSONObject(requestData);
        //JSONObject responseObj=userService.updateUser(user,userId);
        Employee ads=setEmployee(jsonObj);

        service.updateUser(ads, addressId);


        return new ResponseEntity("user updated", HttpStatus.OK);
    }

    private Employee setEmployee(JSONObject data)
    {
        Employee emp=new Employee();
        emp.setEmployee_id(data.getInt("employee_id"));
        emp.setFirst_name(data.getString("first_name"));
        emp.setLast_name(data.getString("last_name"));
        emp.setAddress(data.getString("address"));
        //emp.setAddressId();
        return emp;
    }
}
