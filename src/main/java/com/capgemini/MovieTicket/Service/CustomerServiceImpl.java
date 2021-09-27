package com.capgemini.MovieTicket.Service;


import com.capgemini.MovieTicket.Model.Customer;
import com.capgemini.MovieTicket.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        System.out.println("CustomerRepository Child Class Created By Spring Boot: " + customerRepository.getClass().getName());
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) {

        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer fetchById(int id) {
        Customer customer = null;
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent())
            customer = optionalCustomer.get();
        return customer;
    }

    @Override
    public void udpateCustomer(int id, Customer customer) {
        Customer cust = null;
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent())
            cust = optionalCustomer.get();

        customerRepository.save(cust);
    }
    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
