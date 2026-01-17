package com.example.jpa_test_web_structure.respository;

import com.example.jpa_test_web_structure.domain.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findById(long id);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
