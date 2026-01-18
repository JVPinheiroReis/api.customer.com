package com.example.jpa_test_web_structure.controller;

import com.example.jpa_test_web_structure.domain.Customer;
import com.example.jpa_test_web_structure.service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public final CustomerService service;

    CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> list(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName
    ) {
        return service.listCustomers(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Optional<Customer> get(@PathVariable Long id) {
        return service.findCustomerById(id);
    }

    @PostMapping
    public void create(@RequestBody Customer customer) {
        service.createCustomer(customer);
    }
}
