package com.example.Employee.dao;

import com.example.Employee.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(value="select * from address where address_id= :addressId",nativeQuery = true)
    public List<Address> getByAddressId(long addressId);
}
