package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.exceptions.InvalidParameterException;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    @GetMapping()
    public List<Customer> getCustomers() {
        return db.stream().filter(customer -> !customer.getDeleted()).toList();
    }

    @GetMapping("/{id}")
    public Customer getCustomersById(@PathVariable int id) {
        if (id <1){
            throw new InvalidParameterException("Id must be positive");
        }
            return (getById(id));
    }

    @PostMapping()
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        var location = UriComponentsBuilder.fromPath("/customers/" + id).build().toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
            var foundCustomer = getById(id);
            foundCustomer.setDeleted(true);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
            var foundCustomer = getById(id);
            foundCustomer.setFirstName(customer.getFirstName());
            foundCustomer.setLastName(customer.getLastName());
            foundCustomer.setBirthDate(customer.getBirthDate());
            return foundCustomer;
    }

    private Customer getById(int id){
        var optional = db.stream().filter(customer1 -> customer1.getId() == id).findFirst();
        if (optional.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return optional.get();
    }

}
