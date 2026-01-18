package com.example.jpa_test_web_structure.service;

import com.example.jpa_test_web_structure.domain.Customer;
import com.example.jpa_test_web_structure.domain.CustomerDTO;
import com.example.jpa_test_web_structure.respository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = dto.toEntity();
        Customer savedCustomer = repository.save(customer);
        return CustomerDTO.fromEntity(savedCustomer);
    }

    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = repository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Customer with id " + id + " not found!"
                )
            );
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());

        Customer updatedCustomer = repository.save(customer);
        return CustomerDTO.fromEntity(updatedCustomer);
    }

    public CustomerDTO findCustomerById(Long id) {
        return repository
            .findById(id)
            .map(CustomerDTO::fromEntity)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Customer with id " + id + " not found!"
                )
            );
    }

    public List<CustomerDTO> listCustomers(String firstName, String lastName) {
        List<Customer> customers;
        boolean hasFirst = firstName != null && !firstName.isBlank();
        boolean hasLast = lastName != null && !lastName.isBlank();

        if (hasFirst && hasLast) {
            customers = repository.findByFirstNameAndLastName(
                firstName,
                lastName
            );
        } else if (hasFirst) {
            customers = repository.findByFirstName(firstName);
        } else if (hasLast) {
            customers = repository.findByLastName(lastName);
        } else {
            customers = repository.findAll();
        }
        return customers.stream().map(CustomerDTO::fromEntity).toList();
    }
}
