package ru.shop.repository;

import ru.shop.interfaces.Repository;
import ru.shop.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {
    protected ArrayList<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }
}
