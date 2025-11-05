package com.onlinefood.services;

import com.onlinefood.model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    private int nextCustomerId = 1;
    public Customer addCustomer(String name, String address) {
        Customer c = new Customer(nextCustomerId++, name, address);
        customers.add(c);
        return c;
    }
    public Optional<Customer> findById(int id) { return customers.stream().filter(c->c.getUserId()==id).findFirst(); }
    public List<Customer> getAll() { return customers; }
}
