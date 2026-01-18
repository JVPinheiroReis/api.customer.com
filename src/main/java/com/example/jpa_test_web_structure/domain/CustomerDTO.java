package com.example.jpa_test_web_structure.domain;

public record CustomerDTO(Long id, String firstName, String lastName) {
    public static CustomerDTO fromEntity(Customer customer) {
        return new CustomerDTO(
            customer.getId(),
            customer.getFirstName(),
            customer.getLastName()
        );
    }

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(this.id());
        customer.setFirstName(this.firstName());
        customer.setLastName(this.lastName());
        return customer;
    }
}
