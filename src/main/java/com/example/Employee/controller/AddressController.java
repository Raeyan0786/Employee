package com.example.Employee.controller;

import com.example.Employee.model.Address;
import com.example.Employee.service.AddressService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/v1/address")
public class AddressController {

    @Autowired
    AddressService service;

    @PostMapping(value="/create-address")
    public ResponseEntity<String> createAddress(@RequestBody String data)
    {
        JSONObject jsonObj=new JSONObject(data);
        Address address=setAddress(jsonObj);
        service.addAddress(address);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }

    @GetMapping(value="/getAddress")
    public ResponseEntity<String> getAddress(@Nullable @RequestParam String addressId)
    {
        JSONArray employeeArray= service.getAddress(addressId);
        return new ResponseEntity<>(employeeArray.toString(),HttpStatus.OK);
    }

    @DeleteMapping("/delete-address/{addressId}")
    public ResponseEntity<String> deleteUser(@PathVariable int addressId)
    {
        service.deleteAddress(addressId);
        return new ResponseEntity<>("delete",HttpStatus.OK);
    }

    @PutMapping("/update-address/{addressId}")
    public ResponseEntity<String> upadteAddress(@PathVariable long addressId,@RequestBody String requestData)
    {
        JSONObject jsonObj = new JSONObject(requestData);
        //JSONObject responseObj=userService.updateUser(user,userId);
        Address ads=setAddress(jsonObj);

        service.updateUser(ads, addressId);


        return new ResponseEntity("user updated", HttpStatus.OK);
    }
    public Address setAddress(JSONObject data)
    {
        Address ads=new Address();
        ads.setAddress_id(data.getLong("address_id"));
        ads.setStreet(data.getString("street"));
        ads.setCity(data.getString("city"));
        ads.setState(data.getString("state"));
        ads.setZipcode(data.getString("Zipcode"));
        return ads;
    }
}
