package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerSearchParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    public List <Customer> getAll(CustomerSearchParams searchParams);

    public Customer getById(int id);

    public Customer add(Customer customer);

    public Customer update(int id, Customer customer);

    public  void delete(int id);
}



