package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerSearchParams;
import com.example.demo.exceptions.InvalidParameterException;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService{
    private static int id = 0;
    private List<Customer> db = new ArrayList<>();

    public List <Customer> getAll(CustomerSearchParams customerSearchParams){
        String firstName = customerSearchParams.getFirstName();
        String lastName = customerSearchParams.getLastName();
        LocalDate birthDate = customerSearchParams.getBirthDate();
        var stream =db.stream().filter(customer -> !customer.getDeleted());
        if (firstName!=null && !firstName.isEmpty()) {
           stream= stream.filter(customer -> customer.getFirstName().equals(firstName));
        }
        if (lastName !=null && !lastName.isEmpty()){
            stream= stream.filter(customer -> customer.getLastName().equals(lastName));
        }
        if (birthDate !=null){
            stream= stream.filter(customer -> customer.getBirthDate().isEqual(birthDate));
        }
        return stream.toList();
    }

    public Customer getById(int id){
        if (id <1){
            throw new InvalidParameterException("Id must be positive");
        }
        var optional = db.stream().filter(customer1 -> customer1.getId() == id).findFirst();
        if (optional.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return optional.get();
    }

    public Customer add(Customer customer){
        customer.setId(++id);
        customer.setDeleted(false);
        db.add(customer);
        return customer;
    }

    public Customer update(int id, Customer customer){
        var foundCustomer = getById(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setBirthDate(customer.getBirthDate());
        return customer;
    }

    public  void delete(int id){
        var foundCustomer = getById(id);
        foundCustomer.setDeleted(true);
    }


}
