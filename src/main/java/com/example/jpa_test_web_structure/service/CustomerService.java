package com.example.jpa_test_web_structure.service;

import com.example.jpa_test_web_structure.domain.Customer;
import com.example.jpa_test_web_structure.respository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer) {
        repository.save(customer);
    }

    public List<Customer> listCustomers(String firstName, String lastName) {
        boolean hasFirst = firstName != null && !firstName.isBlank();
        boolean hasLast = lastName != null && !lastName.isBlank();

        if (hasFirst && hasLast) {
            return repository.findByFirstNameAndLastName(firstName, lastName);
        }
        if (hasFirst) {
            return repository.findByFirstName(firstName);
        }
        if (hasLast) {
            return repository.findByLastName(lastName);
        }
        return repository.findAll();
    }

    public Optional<Customer> findCustomerById(Long id) {
        return repository.findById(id);
    }
}
