package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return db.stream().filter(customer -> !customer.getDeleted()).toList();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomersById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(getById(id));
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        var location = UriComponentsBuilder.fromPath("/customers/" + id).build().toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> delete(@PathVariable int id) {
        try {
            var foundCustomer = getById(id);
            foundCustomer.setDeleted(true);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable int id) {
        try {
            var foundCustomer = getById(id);
            foundCustomer.setFirstName(customer.getFirstName());
            foundCustomer.setLastName(customer.getLastName());
            foundCustomer.setBirthDate(customer.getBirthDate());
            return ResponseEntity.ok(foundCustomer);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Customer getById(int id) throws NotFoundException {
        var optional = db.stream().filter(customer1 -> customer1.getId() == id).findFirst();
        if (optional.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return optional.get();
    }

}
