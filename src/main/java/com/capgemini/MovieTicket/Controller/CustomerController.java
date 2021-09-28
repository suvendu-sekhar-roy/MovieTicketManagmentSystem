package com.capgemini.MovieTicket.Controller;

import com.capgemini.MovieTicket.Model.Customer;
import com.capgemini.MovieTicket.Service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//RESTful APIs
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Inside Customer %s", customer);
        Customer cust = customerService.addCustomer(customer);
        return new ResponseEntity<>(cust, HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer/{id}")
    public Customer update(@PathVariable int id,@Valid @RequestBody Customer customer){
        log.info("Updating a customer!!");
        customerService.udpateCustomer(id, customer);
        return customer;
    }

    @GetMapping("/showAll")
    public List<Customer> getCustomers() {
        log.info("INSIDE getCustomers!!");
        return customerService.getAll();
    }

    @GetMapping("/showById/{id}")
    public Customer fetchById(@PathVariable int id) {
        return customerService.fetchById(id);
    }


    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        log.info("Deleting a customer with id %d", id);
        customerService.deleteCustomer(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}