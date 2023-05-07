package com.example.Employee.service;

import com.example.Employee.dao.AddressRepository;
import com.example.Employee.model.Address;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public void addAddress(Address address)
    {
        repository.save(address);
    }

    public JSONArray getAddress(String addressId)
    {

        JSONArray addressArray=new JSONArray();
        if(addressId!=null)
        {
            List<Address> addresslist=repository.getByAddressId(Long.valueOf(addressId));
            for(Address ads:addresslist)
            {
                JSONObject addressObj=createObj(ads);
                addressArray.put(addressObj);
            }
        }
        else
        {
            List<Address> addressList =repository.findAll();
            for(Address ads:addressList)
            {
                JSONObject addressObj=createObj(ads);
                addressArray.put(addressObj);
            }
        }
        return addressArray;
    }

    public void deleteAddress(int addressId)
    {
        repository.deleteById((long)addressId);
    }

    public Address updateUser(Address newAddress, long addressId)
    {
        List<Address> usersList = repository.getByAddressId(addressId);
        JSONObject obj = new JSONObject();
        Address newAds=usersList.get(0);
        newAds.setAddress_id(newAddress.getAddress_id());
        newAds.setStreet(newAddress.getStreet());
        newAds.setCity(newAddress.getCity());
        newAds.setState(newAddress.getState());
        newAds.setZipcode(newAddress.getZipcode());
        repository.save(newAds);
        return newAds;

    }
    public JSONObject createObj(Address address)
    {
        JSONObject jsonObj =new JSONObject();
        jsonObj.put("address_id",address.getAddress_id());
        jsonObj.put("street",address.getStreet());
        jsonObj.put("city",address.getCity());
        jsonObj.put("state",address.getState());
        jsonObj.put("Zipcode",address.getZipcode());
        return jsonObj;
    }
}
