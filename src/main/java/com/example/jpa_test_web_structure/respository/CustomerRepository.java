package com.example.jpa_test_web_structure.respository;

import com.example.jpa_test_web_structure.domain.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository
    extends
        PagingAndSortingRepository<Customer, Long>,
        CrudRepository<Customer, Long>
{
    Customer findById(long id);
    List<Customer> findByFirstName(@Param("name") String firstName);
    List<Customer> findByLastName(@Param("name") String lastName);
}
