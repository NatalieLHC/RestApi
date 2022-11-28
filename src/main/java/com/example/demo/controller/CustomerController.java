package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerSearchParams;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerController  {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping()
    public List<Customer> getAll(CustomerSearchParams searchParams) {
        return customerService.getAll(searchParams);
    }

    @GetMapping("/{id}")
    public Customer getCustomersById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        customerService.add(customer);
        var location = UriComponentsBuilder.fromPath("/customers/" + customer.getId()).build().toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
        return customerService.update(id,customer);
    }


}
