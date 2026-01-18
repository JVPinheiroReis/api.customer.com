package com.example.jpa_test_web_structure.controller;

import com.example.jpa_test_web_structure.domain.CustomerDTO;
import com.example.jpa_test_web_structure.service.CustomerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<CustomerDTO>> list(
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName
    ) {
        return ResponseEntity.ok(service.listCustomers(firstName, lastName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(service.createCustomer(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(
        @PathVariable Long id,
        @RequestBody CustomerDTO dto
    ) {
        return ResponseEntity.ok(service.updateCustomer(id, dto));
    }
}
